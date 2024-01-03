# Domain/Grade.py

class Grade:
    def __init__(self, course, numeric_grade):
        self.course = course
        self.num_grade = numeric_grade
        self._is_passed = None  # Initialize is_passed as None

        self.letter_grade_calculation()

    def letter_grade_calculation(self):
        if self.num_grade >= 88:
            self._is_passed = True
            self.letter_grade = "AA"
            self.numeric_grade_for_gano = 4
        elif self.num_grade >= 81:
            self._is_passed = True
            self.letter_grade = "BA"
            self.numeric_grade_for_gano = 3.5
        elif self.num_grade >= 74:
            self._is_passed = True
            self.letter_grade = "BB"
            self.numeric_grade_for_gano = 3
        elif self.num_grade >= 67:
            self._is_passed = True
            self.letter_grade = "CB"
            self.numeric_grade_for_gano = 2.5
        elif self.num_grade >= 60:
            self._is_passed = True
            self.letter_grade = "CC"
            self.numeric_grade_for_gano = 2
        elif self.num_grade >= 53:
            self._is_passed = True
            self.letter_grade = "DC"
            self.numeric_grade_for_gano = 1.5
        elif self.num_grade >= 46:
            self._is_passed = True
            self.letter_grade = "DD"
            self.numeric_grade_for_gano = 1
        else:
            self._is_passed = False
            self.letter_grade = "FF"
            self.numeric_grade_for_gano = 0


    def get_course(self):
        return self.course

    def get_numeric_grade(self):
        return self.num_grade

    def get_letter_grade(self):
        return self.letter_grade

    def is_passed(self):
        return self._is_passed

    def set_passed(self, passed):
        self._is_passed = passed

    def set_numeric_grade(self, numeric_grade):
        self.num_grade = numeric_grade
        self.letter_grade_calculation()

    def get_numeric_grade_for_gano(self):
        return self.numeric_grade_for_gano



import json

class GradeEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, Grade):
            return {
                'course': obj.get_course(),
                'numeric_grade': obj.get_numeric_grade(),
                'letter_grade': obj.get_letter_grade(),
                'is_passed': obj.is_passed(),
                'numeric_grade_for_gano': obj.get_numeric_grade_for_gano()
            }
        return super().default(obj)
