from Domain.Department import Department
from Login.ILoginStrategy import ILoginStrategy
from IDGenerators.RegistrationIDGenerator import RegistrationIDGenerator
from Login.LoginHelper import LoginHelper
import logging

class StudentLoginStrategy(ILoginStrategy):
    def login(self, department):
        self.login_as_a_student(department)

    def login_as_a_student(self, department):
        regis_id_generator = RegistrationIDGenerator()
        logger = logging.getLogger()
        var = -1
        flag = False
        student = None
        login_helper = LoginHelper()

        while not flag:
            print("Enter your student number")
            print("0 to cancel")

            entered_no = input()

            if entered_no == "0":
                return

            student = department.get_student_by_student_no(entered_no)
            if student is None:
                logger.info(f"Student login attempt unsuccesfull. Student not found with {entered_no}")
                continue
            else:
                print("Enter your password:")
                entered_password = input()
                if entered_password != student.get_password():
                    print("Wrong password try again")
                    logger.info(f"Student login attempt unsuccesfull. Student with number no {entered_no} entered invalid password.")
                    continue
            flag = True
        logger.info(f"Student with mnumber {entered_no} loginned.");
        choice = -1
        while choice != 0:
            print("0 to exit")
            print("1 to list courses taken")
            print("2 to print transcript")
            print("3 to register for a new course")
            print("4 to see available courses")

            try:
                choice = int(input())
            except ValueError as e:
                print(e)

            if choice == 0:
                break
            elif choice == 1:
                # get courses taken
                for code in student.get_course_codes():
                    print(department.get_course_by_course_code(code))
            elif choice == 2:
                # display transcript
                student.get_transcript().print_taken_courses_status()
            elif choice == 3:
                # new course registration
                print("Enter the course code")
                course_code = input()
                try:
                    student.check_course_eligibility(course_code)  # THROWS ERROR IF NOT ELIGIBLE
                    selected_course_section_no = self.display_course_selection(course_code)
                    student.register_to_new_course(course_code, regis_id_generator.generate_id(), selected_course_section_no)

                except Exception as e:
                    print(e)
                    logger.info(f"{e} for student with number {entered_no}")

            elif choice == 4:
                print("Available courses:")
                login_helper.print_available_courses(student)
            else:
                # default case
                break

    def display_course_selection(self, course_code):
        course = Department.get_instance().get_course_by_course_code(course_code)
        course.print_course_sections()

        print("Enter the section number:")
        chosen_section_no = input()

        return chosen_section_no
