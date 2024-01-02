from Course import Course
from Student import Student
from CourseSection import CourseSection
from Lecturer import Lecturer
from Advisor import Advisor
from Department import Department
from RegistrationStatus import RegistrationStatus
from IDGenerators.StudentIDGenerator import StudentIDGenerator
from IDGenerators.StaffIDGenerator import StaffIDGenerator
from IDGenerators.RegistrationIDGenerator import RegistrationIDGenerator

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
''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

yusuf.register_to_new_course(database.get_course_code(), registrationIDGenerator.generate_id(), section1.get_course_section_no())
corut.proceed_the_registration(Department.get_instance().get_all_registrations()[0].get_registration_no(), RegistrationStatus.Confirmed)

try:
    yusuf.check_course_eligibility(database.get_course_code())
    yusuf.register_to_new_course(database.get_course_code(), registrationIDGenerator.generate_id(),
                                 section1.get_course_section_no())
    #yusuf.register_to_new_course(digital.get_course_code(), registrationIDGenerator.generate_id(), section2.get_course_section_no())
    corut.proceed_the_registration(Department.get_instance().get_all_registrations()[1].get_registration_no(),
                                   RegistrationStatus.Confirmed)

except Exception as e:
    print(e)


print(' ')

Department.get_instance().get_all_course_sections()[0].print_sections()
Department.get_instance().get_all_course_sections()[1].print_sections()

