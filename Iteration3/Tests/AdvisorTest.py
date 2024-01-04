import unittest

from Domain.Advisor import Advisor
from Domain.Course import Course
from Domain.CourseSection import CourseSection
from Domain.Department import Department
from Domain.Registration import Registration
from Domain.RegistrationStatus import RegistrationStatus
from Domain.Student import Student


class MyTestCase(unittest.TestCase):
    def test_confirm_registration(self):
        self.student.register_to_new_course("CSE3001", "R1111", "1")
        self.advisor.proceed_the_registration("R1111", RegistrationStatus.Confirmed)
        self.assertIn("CSE3001", self.student.courseCodes)
        registration : Registration = Department.get_instance().get_registration_by_registration_no("R1111")
        self.assertEqual(registration.status, RegistrationStatus.Confirmed)

    def test_reject_registration(self):
        self.student.register_to_new_course("CSE3001", "R1111", "1")
        self.advisor.proceed_the_registration("R1111", RegistrationStatus.Rejected)
        self.assertNotIn("CSE3001", self.student.courseCodes)
        registration : Registration = Department.get_instance().get_registration_by_registration_no("R1111")
        self.assertEqual(registration.status, RegistrationStatus.Rejected)

    def test_active_registration(self):
        self.student.register_to_new_course("CSE3001", "R1111", "1")
        self.advisor.proceed_the_registration("R1111", RegistrationStatus.Active)
        self.assertNotIn("CSE3001", self.student.courseCodes)
        registration: Registration = Department.get_instance().get_registration_by_registration_no("R1111")
        self.assertEqual(registration.status, RegistrationStatus.Active)


    def setUp(self):
        Department._department = None;
        self.student: Student = Student("Kerim", "YILDIRIM", "120040", [], "10063850", [], 6, "12345")
        self.advisor: Advisor = Advisor("Ahmet Mehmet", "SARI", "10063850", ['CSE3001', 'CSE3002'], ['120040'], [],
                                        "12345")
        self.courseSection1: CourseSection = CourseSection("CSE3001", "1", 30, ["415","416"], 28)
        self.courseSection2: CourseSection = CourseSection("CSE3002", "2", 2, ["414","415"], 0)
        self.courseSection3:CourseSection = CourseSection("CSE3003", "3", 2, ["311", "415"], 0)

        self.course1: Course = Course("Very Important Course", "CSE3001", ['10063850'], [self.courseSection1], [], [],
                                      15, 5)
        self.course2: Course = Course("Not Very Important Course", "CSE3002", ['10063850'], [self.courseSection2], [],
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


if __name__ == '__main__':
    unittest.main()



