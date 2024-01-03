from Domain.Person import Person
from Domain.Course import Course
from Domain.Staff import Staff
from Domain.Student import Student
from Domain.CourseSection import CourseSection
from Domain.Lecturer import Lecturer
from Domain.Registration import Registration
from Domain.Advisor import Advisor


class Department:
    _department = None

    def __init__(self):
        self.all_people = []
        self.all_courses = []
        self.all_registrations = []
        self.all_staffs = []
        self.all_students = []
        self.all_lecturers = []
        self.all_advisors = []
        self.all_course_sections = []

    def get_course_section_by_section_no(self, course_section_no):
        for section in self.all_course_sections:
            if section.get_course_section_no() == course_section_no:
                return section
        print(f"{course_section_no} is not found")
        return None

    def get_advisor_by_staff_no(self, staff_no):
        for advisor in self.all_advisors:
            if advisor.get_staff_no() == staff_no:
                return advisor
        print(f"{staff_no} is not found")
        return None

    def get_course_by_course_code(self, course_code):
        for course in self.all_courses:
            if course.get_course_code() == course_code:
                return course
        print(f"{course_code} is not found")
        return None

    def get_lecturer_by_staff_no(self, staff_no):
        for lecturer in self.all_lecturers:
            if lecturer.get_staff_no() == staff_no:
                return lecturer
        print(f"Staff with staff no {staff_no} is not found")
        return None

    def get_registration_by_registration_no(self, registration_no):
        for registration in self.all_registrations:
            if registration.get_registration_no() == registration_no:
                return registration
        print(f"{registration_no} is not found")
        return None

    def get_student_by_student_no(self, student_no):
        for student in self.all_students:
            if student.get_student_no() == student_no:
                return student
        print(f"Student with studentNo {student_no} is not found")
        return None

    @classmethod
    def get_instance(cls):
        if not cls._department:
            cls._department = Department()
        return cls._department

    def add_an_object(self, obj):
        if isinstance(obj, Person):
            self.all_people.append(obj)
        if isinstance(obj, CourseSection):
            self.all_course_sections.append(obj)
        if isinstance(obj, Course):
            self.all_courses.append(obj)
        if isinstance(obj, Student):
            self.all_students.append(obj)
        if isinstance(obj, Advisor):
            self.all_lecturers.append(obj)
        if isinstance(obj, Lecturer):
            self.all_advisors.append(obj)
        if isinstance(obj, Registration):
            self.all_registrations.append(obj)
        if isinstance(obj, Staff):
            self.all_staffs.append(obj)
        if not (isinstance(obj, Course) or isinstance(obj, Staff) or isinstance(obj, Registration)
                or isinstance(obj, Advisor) or isinstance(obj, Lecturer) or isinstance(obj, Student)
                or isinstance(obj, Course) or isinstance(obj, CourseSection) or isinstance(obj, Person)):
            # Your code when the condition is False

            print(f"{type(obj).__name__} could not be added")

    def get_all_people(self):
        return self.all_people

    def set_all_people(self, all_people):
        self.all_people = all_people

    def get_all_courses(self):
        return self.all_courses

    def set_all_courses(self, all_courses):
        self.all_courses = all_courses

    def get_all_registrations(self):
        return self.all_registrations

    def set_all_registrations(self, all_registrations):
        self.all_registrations = all_registrations

    def get_all_staffs(self):
        return self.all_staffs

    def set_all_staffs(self, all_staffs):
        self.all_staffs = all_staffs

    def get_all_students(self):
        return self.all_students

    def set_all_students(self, all_students):
        self.all_students = all_students

    def get_all_lecturers(self):
        return self.all_lecturers

    def set_all_lecturers(self, all_lecturers):
        self.all_lecturers = all_lecturers

    def get_all_advisors(self):
        return self.all_advisors

    def set_all_advisors(self, all_advisors):
        self.all_advisors = all_advisors

    def get_all_course_sections(self):
        return self.all_course_sections

    def set_all_course_sections(self, all_course_sections):
        self.all_course_sections = all_course_sections
