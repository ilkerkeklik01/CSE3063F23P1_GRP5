from Data.DataManager import DataManager
from Domain.Course import Course
from Domain.Student import Student
from Domain.CourseSection import CourseSection
from Domain.Lecturer import Lecturer
from Domain.Advisor import Advisor
from Domain.Department import Department
from Domain.RegistrationStatus import RegistrationStatus
from IDGenerators.StudentIDGenerator import StudentIDGenerator
from IDGenerators.StaffIDGenerator import StaffIDGenerator
from IDGenerators.RegistrationIDGenerator import RegistrationIDGenerator
from Logging.LogFormatter import CustomFormatter
from Login.LoginContext import LoginContext
from Login.StudentLoginStrategy import StudentLoginStrategy
from Login.AdvisorLoginStrategy import AdvisorLoginStrategy
from Login.LecturerLoginStrategy import LecturerLoginStrategy
from Domain.Department import Department
import logging

studentIDGenerator = StudentIDGenerator()
staffIDGenerator = StaffIDGenerator()
registrationIDGenerator = RegistrationIDGenerator()

def main():
    logging.basicConfig(filename='courseRegistration.log', level=logging.INFO, format='%(levelname)s - %(message)s')
    formatter = CustomFormatter()
    for handler in logging.root.handlers:
        handler.setFormatter(formatter)

    data_manager = DataManager()
    data_manager.load_data()

    department = Department().get_instance();




    display_console_ui(Department.get_instance())


    #data_manager.save_data()



def display_console_ui(department):
    data_manager = DataManager()
    choice = -1

    login_context = LoginContext()

    while choice != 0:
        print("Press 1 to login as a student")
        print("Press 2 to login as a lecturer")
        print("Press 3 to login as an advisor")
        print("Press 0 to exit!")

        try:
            choice = int(input())
        except ValueError as e:
            print(e)

        if choice == 0:
            data_manager.save_data()
            exit(0)
        elif choice == 1:
            login_context.set_strategy(StudentLoginStrategy())
            login_context.login(department)
        elif choice == 2:
            login_context.set_strategy(LecturerLoginStrategy())
            login_context.login(department)
        elif choice == 3:
            login_context.set_strategy(AdvisorLoginStrategy())
            login_context.login(department)

if __name__ == "__main__":
    main()
