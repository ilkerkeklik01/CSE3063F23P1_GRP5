from Domain.RegistrationStatus import RegistrationStatus
from Login.ILoginStrategy import ILoginStrategy
from Login.LoginHelper import LoginHelper
import logging

class AdvisorLoginStrategy(ILoginStrategy):
    def login(self, department):
        self.login_as_an_advisor(department)

    def login_as_an_advisor(self, department):
        logger = logging.getLogger()
        var = -1
        flag = False
        advisor = None
        login_helper = LoginHelper()

        while var != 0 and not flag:
            print("Enter your staff number")
            print("0 to cancel")

            entered_no = input()

            if entered_no == "0":
                return

            advisor = department.get_advisor_by_staff_no(entered_no)
            if advisor is None:
                logger.info(f"Advisor login attempt unsuccesfull. Advisor not found with {entered_no}")

                continue
            else:
                print("Enter your password:")
                entered_password = input()
                if entered_password != advisor.get_password():
                    logger.info(f"Advisor login attempt unsuccesfull. Advisor with {entered_no} entered wrong password")
                    print("Wrong password try again")
                    continue
            flag = True
        logger.info(f"Advisor login attempt successfull. Advisor with {entered_no} loginned")

        choice = -1
        while choice != 0:
            print("0 to exit")
            print("1 to list active registrations")
            print("2 to list students you advise")
            print("3 to proceed a registration")
            print("4 to list teaching courses")
            print("5 to grade ")
            print("")
            try:
                choice = int(input())
            except ValueError as e:
                print(e)

            if choice == 0:
                break
            elif choice == 1:  # get active registrations
                for registration in advisor.get_active_registrations():
                    print(registration)
            elif choice == 2:  # list students you advise
                for code in advisor.get_student_numbers():
                    print(department.get_student_by_student_no(code))
            elif choice == 3:  # proceed a registration
                try:
                    registration_no = input("Enter registration no.: ")
                    registration_status_str = input("Enter status (Active-Rejected-Confirmed): ")
                except Exception as e:
                    print(e)
                    logger.info(f"{e} for advisor {entered_no}")
                    break

                status = None
                if registration_status_str.lower() == "confirmed":
                    status = RegistrationStatus.Confirmed
                    logger.info(f"Advisor {entered_no} confirmed registration: {registration}")
                elif registration_status_str.lower() == "active":
                    status = RegistrationStatus.Active
                    logger.info(f"Advisor {entered_no} did not change the status of registration: {registration}")
                elif registration_status_str.lower() == "rejected":
                    logger.info(f"Advisor {entered_no} rejected registration: {registration}")

                    status = RegistrationStatus.Rejected
                else:
                    print("Invalid registration status!")
                    logger.info(f"Advisor {entered_no} entered invalid registration status")
                    continue

                advisor.proceed_the_registration(registration_no, status)
            elif choice == 4:
                login_helper.list_courses_for_lecturer(department, advisor)
            else:
                # default case
                break
