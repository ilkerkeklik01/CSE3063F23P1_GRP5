import unittest

from Domain.Advisor import Advisor
from Domain.Course import Course
from Domain.CourseSection import CourseSection
from Domain.Department import Department
from Domain.Lecturer import Lecturer
from Domain.RegistrationStatus import RegistrationStatus
from Domain.Student import Student


class MyTestCase(unittest.TestCase):
    def test_get_course(self):
        courses = self.lecturer.get_course_ids()
        self.assertEqual(['CSE3001', 'CSE3002'], courses )

    def setUp(self):
        Department._department = None;
        self.student: Student = Student("Kerim", "YILDIRIM", "120040", [], "10063850", [], 6, "12345")
        self.advisor: Advisor = Advisor("Ahmet Mehmet", "SARI", "10063850", ['CSE3001', 'CSE3002'], ['120040'], [],
                                        "12345")
        self.lecturer: Lecturer = Lecturer("Emir", "Şanlı", "10063450", ['CSE3001', 'CSE3002'], ['12345'])
        self.courseSection1: CourseSection = CourseSection("CSE3001", "1", 30, ["415", "416"], 28)
        self.courseSection2: CourseSection = CourseSection("CSE3002", "2", 2, ["414", "415"], 0)
        self.courseSection3: CourseSection = CourseSection("CSE3003", "3", 2, ["311", "415"], 0)

        self.course1: Course = Course("Very Important Course", "CSE3001", ['10063850', '10063450'], [self.courseSection1], [], [],
                                      15, 5)
        self.course2: Course = Course("Not Very Important Course", "CSE3002", ['10063850', '10063450'], [self.courseSection2], [],
                                      ["CSE3001"], 3, 5)
        self.course3: Course = Course("Higher Course", "CSE3003", ['10063850'], [self.courseSection3], [], [], 3, 7)

        department = Department.get_instance()
        Department.get_instance().add_an_object(self.course1)
        Department.get_instance().add_an_object(self.course2)
        Department.get_instance().add_an_object(self.course3)

        Department.get_instance().add_an_object(self.courseSection1)
        Department.get_instance().add_an_object(self.courseSection2)
        Department.get_instance().add_an_object(self.courseSection3)
        Department.get_instance().add_an_object(self.advisor)
        Department.get_instance().add_an_object(self.student)
        Department.get_instance().add_an_object(self.lecturer)

        self.student.register_to_new_course("CSE3001", "R1111", "1")
        self.advisor.proceed_the_registration("R1111", RegistrationStatus.Confirmed)



if __name__ == '__main__':
    unittest.main()
