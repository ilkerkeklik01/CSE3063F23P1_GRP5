import unittest

from Domain.Advisor import Advisor
from Domain.Course import Course
from Domain.CourseSection import CourseSection
from Domain.Department import Department
from Domain.Grade import Grade
from Domain.RegistrationStatus import RegistrationStatus
from Domain.Student import Student
from Domain.Transcript import Transcript


class MyTestCase(unittest.TestCase):

    def test_transcript_add_grade(self):
        self.student.register_to_new_course("CSE3001", "R1111", "1")
        self.advisor.proceed_the_registration("R1111", RegistrationStatus.Confirmed)


        transcript : Transcript = self.student.transcript;
        transcript.add_grade("CSE3001", 100);
        grades = transcript.get_grades()

        def filter_condition(grade):
            return grade.course.get_course_code() == "CSE3001"

        grade : Grade = next(filter(filter_condition, grades), None)

        self.assertEqual(grade.get_letter_grade(), "AA")
        self.assertEqual(grade.is_passed(), True)

    def test_transcript_add_grade_to_change(self):
        self.student.register_to_new_course("CSE3001", "R1111", "1")
        self.advisor.proceed_the_registration("R1111", RegistrationStatus.Confirmed)


        transcript: Transcript = self.student.transcript;
        transcript.add_grade("CSE3001", 100);
        grades = transcript.get_grades()

        def filter_condition(grade):
            return grade.course.get_course_code() == "CSE3001"

        grade: Grade = next(filter(filter_condition, grades), None)

        self.assertEqual(grade.get_letter_grade(), "AA")
        self.assertEqual(grade.is_passed(), True)

        transcript.add_grade("CSE3001", 50);

        self.assertEqual(grade.get_letter_grade(), "DD")
        self.assertEqual(grade.is_passed(), True)

        transcript.add_grade("CSE3001", 45)

        self.assertEqual(grade.get_letter_grade(), "FF")
        self.assertEqual(grade.is_passed(), False)

    def test_transcript_pass_course(self):
        self.student.register_to_new_course("CSE3001", "R1111", "1")
        self.advisor.proceed_the_registration("R1111", RegistrationStatus.Confirmed)
        self.student.transcript.add_grade("CSE3001", 100)
        self.student.register_to_new_course("CSE3002", "R1112", "2")
        self.advisor.proceed_the_registration("R1112", RegistrationStatus.Confirmed)

    def test_transcript_not_pass_course(self):
        self.student.register_to_new_course("CSE3001", "R1111", "1")
        self.advisor.proceed_the_registration("R1111", RegistrationStatus.Confirmed)
        self.student.transcript.add_grade("CSE3001", 20)
        with self.assertRaises(Exception) as context:
            self.student.register_to_new_course("CSE3002", "R1112", "2")
            self.advisor.proceed_the_registration("R1112", RegistrationStatus.Confirmed)




    def setUp(self):
        Department._department = None;
        self.student: Student = Student("Kerim", "YILDIRIM", "120040", [], "10063850", [], 6, "12345")
        self.advisor: Advisor = Advisor("Ahmet Mehmet", "SARI", "10063850", ['CSE3001', 'CSE3002'], ['120040'], [],
                                        "12345")
        self.courseSection1: CourseSection = CourseSection("CSE3001", "1", 30, ["415", "416"], 28)
        self.courseSection2: CourseSection = CourseSection("CSE3002", "2", 2, ["414", "314"], 0)
        self.courseSection3: CourseSection = CourseSection("CSE3003", "3", 2, ["311", "415"], 0)

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
