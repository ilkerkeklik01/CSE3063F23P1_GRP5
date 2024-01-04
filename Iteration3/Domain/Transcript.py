from Domain.Grade import Grade  # Assuming you have a Grade class in your project
 # Assuming you have a Department class in your project

class Transcript:
    def __init__(self):
        self.completed_credits = 0
        self.grades = []

    def update_gano(self):
        total_numerical_grade = sum(grade.get_numeric_grade_for_gano() for grade in self.grades)
        updated_gano = total_numerical_grade / self.completed_credits if self.completed_credits != 0 else 0
        self.set_gano(updated_gano)

    def add_grade(self, course_code, numeric_grade):
        from Domain.Department import Department
        course = Department.get_instance().get_course_by_course_code(course_code)

        for grade in self.grades:
            if grade.get_course().get_course_code() == course_code:
                grade.set_numeric_grade(numeric_grade)
                return

        new_grade = Grade(course, numeric_grade)
        self.grades.append(new_grade)
        self.update_total_credits_and_gano()

    def update_total_credits_and_gano(self):
        total_credits = sum(grade.get_course().get_credit() for grade in self.grades)
        self.set_completed_credits(total_credits)
        self.update_gano()

    # region Print Methods

    def print_taken_courses_status(self):
        print("Completed Credits:", self.get_completed_credits(), "\n")
        print("Completed Courses", "\n")
        self.print_passed_courses()
        self.print_failed_courses()

    def print_passed_courses(self):
        for grade in self.grades:
            if grade.is_passed:
                print(f"{grade.get_course().get_course_name()} {grade.get_course().get_course_code()} "
                      f"{grade.get_course().get_credit()} credits {grade.get_letter_grade()}")

    def get_passed_course_codes(self):
        return [grade.get_course().get_course_code() for grade in self.grades if grade.is_passed()]

    def print_failed_courses(self):
        for grade in self.grades:
            if not grade.is_passed:
                print(f"{grade.get_course().get_course_name()} {grade.get_course().get_course_code()} "
                      f"{grade.get_course().get_credit()} credits {grade.get_letter_grade()}")

    # endregion Print Methods

    # region properties

    def get_gano(self):
        self.update_total_credits_and_gano()
        return self.gano

    def set_gano(self, gano):
        self.gano = gano


    def get_grades(self):
        return self.grades

    def set_completed_credits(self, completed_credits):
        self.completed_credits = completed_credits

    def get_completed_credits(self):
        return self.completed_credits

    def get_completed_courses(self):
        return [grade.get_course() for grade in self.grades]

    def get_successfully_completed_course_codes(self):
        return [grade.course.course_code for grade in self.grades if grade.is_passed()]

    def get_failed_course_codes(self):
        return [grade.get_course().get_course_code() for grade in self.grades if not grade.is_passed()]

    def get_all_completed_course_codes(self):
        return [grade.get_course().get_course_code() for grade in self.grades]

    # endregion properties

import json
from Domain.Grade import GradeEncoder  # Assuming you have a Grade class in your project

class TranscriptEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, Transcript):
            return {
                'completed_credits': obj.get_completed_credits(),
                'grades': [GradeEncoder().default(grade) for grade in obj.get_grades()],
                'gano': obj.get_gano()
            }
        return super().default(obj)
