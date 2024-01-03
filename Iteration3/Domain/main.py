from Course import Course
from Student import Student
from CourseSection import CourseSection
from Lecturer import Lecturer
from Advisor import Advisor
from Domain.Department import Department
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
from Domain.Registration import Registration

from Data.DataManager import DataManager

dataManager = DataManager()

reg = Registration("R1", yusuf.get_student_no(), corut.get_staff_no(), database.get_course_code(), RegistrationStatus.Active, section1)
Department.get_instance().add_an_object(reg)

Department.get_instance().get_all_courses().append(database)
Department.get_instance().get_all_courses().append(digital)
Department.get_instance().get_all_course_sections().append(section1)
Department.get_instance().get_all_course_sections().append(section2)
Department.get_instance().get_all_advisors().append(corut)
Department.get_instance().get_all_advisors().append(omer)
Department.get_instance().get_all_students().append(yusuf)
Department.get_instance().get_all_staffs().append(corut)
Department.get_instance().get_all_registrations()
Department.get_instance().get_all_lecturers().append(corut)

dataManager.save_data()


department = Department.get_instance()
