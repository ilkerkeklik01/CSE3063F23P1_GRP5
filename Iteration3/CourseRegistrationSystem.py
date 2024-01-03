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
from Login.LoginContext import LoginContext
from Login.StudentLoginStrategy import StudentLoginStrategy
from Login.AdvisorLoginStrategy import AdvisorLoginStrategy
from Login.LecturerLoginStrategy import LecturerLoginStrategy

studentIDGenerator = StudentIDGenerator()
staffIDGenerator = StaffIDGenerator()
registrationIDGenerator = RegistrationIDGenerator()

corut = Advisor("Fatma", "Corut", staffIDGenerator.generate_id(), [], [], [])
omer = Advisor("Ömer", "Korçak", staffIDGenerator.generate_id(), [], [], [])

mustafa = Lecturer("Mustafa" , "Ağaoğlu", staffIDGenerator.generate_id())

yusuf = Student("Yusuf", "Duman", studentIDGenerator.generate_id(), [], corut.get_staff_no(), [], 8)
kerim = Student("Kerim Hasan", "Yıldırım", studentIDGenerator.generate_id(), [], corut.get_staff_no(), [], 6)
ilker = Student("İlker", "Keklik", studentIDGenerator.generate_id(), [], omer.get_staff_no(), [], 7)

section1 = CourseSection("CSE3200", "1", 40, ["111"])
database = Course("Database", "CSE3200", [], [], [], [], 6)
database.add_section(section1)
database.get_lecturers_numbers().append(mustafa.get_staff_no())

section2 = CourseSection("CSE3000", "2", 40, ["111"])
digital = Course("Digital Logic Design", "CSE3000", [], [], [], [], 6)
digital.add_section(section2)
digital.get_lecturers_numbers().append(corut.get_staff_no())


corut.set_courses([database.get_course_code()])

def main():
    department = Department.get_instance()

    yusuf.register_to_new_course(database.get_course_code(), "R1", section1.get_course_section_no())
    corut.proceed_the_registration("R1", RegistrationStatus.Confirmed)
    yusuf.get_transcript().add_grade("CSE3200", 86)
    yusuf.get_transcript().print_passed_courses()
   # data_manager = DataManager()
    #data_manager.load_data()

    print(yusuf.get_student_no())
    print(yusuf.get_advisor_no())

    display_console_ui(department)

   # data_manager.save_data()

def display_console_ui(department):
  #  data_manager = DataManager()
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
    #        data_manager.save_data()
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
