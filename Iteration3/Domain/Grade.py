class Grade:
    def __init__(self, course, numeric_grade):
        self.course = course
        self.num_grade = numeric_grade
        self.letter_grade_calculation()

    def letter_grade_calculation(self):
        if self.num_grade >= 88:
            self.is_passed = True
            self.letter_grade = "AA"
            self.numeric_grade_for_gano = 4
        elif self.num_grade >= 81:
            self.is_passed = True
            self.letter_grade = "BA"
            self.numeric_grade_for_gano = 3.5
        elif self.num_grade >= 74:
            self.is_passed = True
            self.letter_grade = "BB"
            self.numeric_grade_for_gano = 3
        elif self.num_grade >= 67:
            self.is_passed = True
            self.letter_grade = "CB"
            self.numeric_grade_for_gano = 2.5
        elif self.num_grade >= 60:
            self.is_passed = True
            self.letter_grade = "CC"
            self.numeric_grade_for_gano = 2
        elif self.num_grade >= 53:
            self.is_passed = True
            self.letter_grade = "DC"
            self.numeric_grade_for_gano = 1.5
        elif self.num_grade >= 46:
            self.is_passed = True
            self.letter_grade = "DD"
            self.numeric_grade_for_gano = 1
        else:
            self.is_passed = False
            self.letter_grade = "FF"
            self.numeric_grade_for_gano = 0

    @property
    def get_course(self):
        return self.course

    @property
    def get_numeric_grade(self):
        return self.num_grade

    @property
    def get_letter_grade(self):
        return self.letter_grade

    @property
    def is_passed(self):
        return self.is_passed

    @is_passed.setter
    def is_passed(self, passed):
        self.is_passed = passed

    def set_numeric_grade(self, numeric_grade):
        self.num_grade = numeric_grade
        self.letter_grade_calculation()

    @property
    def get_numeric_grade_for_gano(self):
        return self.numeric_grade_for_gano
