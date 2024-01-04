from Login import ILoginStrategy
from Login.LoginHelper import LoginHelper
from Domain import Department, Lecturer
from Login.ILoginStrategy import ILoginStrategy  # Use relative import
from Login.LoginHelper import LoginHelper  # Assuming LoginHelper is in the same package
import logging
class LecturerLoginStrategy(ILoginStrategy):
    def login(self, department):
        self.login_as_lecturer(department)

    def login_as_lecturer(self, department):
        logger = logging.getLogger()
        var = -1
        flag = False
        lecturer = None
        login_helper = LoginHelper()

        while var != 0 and not flag:
            print("Enter your staff number")
            print("0 to cancel")

            entered_no = input()

            if entered_no == "0":

                return

            lecturer = department.get_lecturer_by_staff_no(entered_no)
            if lecturer is None:
                logger.info(f"Lecturer login unsuccessfull, entered no is {entered_no} is not found")
                continue
            else:
                print("Enter your password:")
                entered_password = input()
                if entered_password != lecturer.get_password():
                    logger.info(f"Lecturer login unsuccessfull, lecturer {entered_no} entered wrong password")
                    print("Wrong password try again")
                    continue
            flag = True
        logger.info(f"Lecturer login successful, lecturer {entered_no} is log inned.")

        choice = -1
        while choice != 0:
            print("0 to exit")
            print("1 to list teaching courses")
            try:
                choice = int(input())
            except ValueError as e:
                print(e)

            if choice == 0:
                break
            elif choice == 1:  # list teaching courses
                login_helper.list_courses_for_lecturer(department, lecturer)
            else:
                # default case
                break
