 # Assuming you have a Department class in your project


class Course:
    def __init__(self, course_name, course_code, lecturers_numbers, course_sections, student_numbers, prerequisites_ids, credit, semester):
        self.credit = credit
        self.course_name = course_name
        self.course_code = course_code
        self.lecturers_numbers = lecturers_numbers
        self.course_sections = course_sections
        self.student_numbers = student_numbers
        self.prerequisites_ids = prerequisites_ids
        self.semester = semester  # Assuming the default value for semester is 0

    # region Methods

    def add_section(self, section):
        if self.course_sections is None:
            self.course_sections = []
        self.course_sections.append(section)

    def print_course_sections(self):
        for each in self.course_sections:
            each.print_sections()

    def get_students(self):
        from Domain.Department import Department
        students = []
        all_students = Department.get_instance().get_all_students()

        for student in all_students:
            if student.get_student_no() in self.get_student_numbers():
                students.append(student)
        return students

    # endregion Methods

    # region Properties

    def get_credit(self):
        return self.credit

    def set_credit(self, credit):
        self.credit = credit

    def get_course_name(self):
        return self.course_name

    def set_course_name(self, course_name):
        self.course_name = course_name

    def get_course_code(self):
        return self.course_code

    def set_course_code(self, course_code):
        self.course_code = course_code

    def get_lecturers_numbers(self):
        return self.lecturers_numbers

    def set_lecturers_numbers(self, lecturers_ids):
        self.lecturers_numbers = lecturers_ids

    def get_course_sections(self):
        return self.course_sections

    def set_course_sections(self, course_sections):
        self.course_sections = course_sections

    def get_student_numbers(self):
        return self.student_numbers

    def set_student_numbers(self, student_numbers):
        self.student_numbers = student_numbers

    def get_prerequisites_ids(self):
        return self.prerequisites_ids

    def set_prerequisites_ids(self, prerequisites_ids):
        self.prerequisites_ids = prerequisites_ids

    def get_semester(self):
        return self.semester

    def set_semester(self, semester):
        self.semester = semester

    # endregion Properties

    def __str__(self):
        return f"Course{{course_name='{self.course_name}', course_code='{self.course_code}', " \
               f"lecturers_numbers={self.lecturers_numbers}, student_numbers={self.student_numbers}, " \
               f"prerequisites_ids={self.prerequisites_ids}, credit={self.credit}, semester={self.semester}}}"


import json
from Domain.Course import Course
from Domain.CourseSection import CourseSectionEncoder

class CourseEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, Course):
            return {
                'credit': obj.get_credit(),
                'course_name': obj.get_course_name(),
                'course_code': obj.get_course_code(),
                'lecturers_numbers': obj.get_lecturers_numbers(),
                'course_section': [CourseSectionEncoder().default(section) for section in obj.get_course_sections()],
                'student_numbers': obj.get_student_numbers(),
                'prerequisites_ids': obj.get_prerequisites_ids(),
                'semester': obj.get_semester()
            }
        return super().default(obj)

