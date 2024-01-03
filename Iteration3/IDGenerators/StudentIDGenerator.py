from IDGenerators.IDGenerator import IDGenerator

class StudentIDGenerator(IDGenerator):
    def __init__(self):
        super().__init__()
        self.set_digit_count(8)
        self.set_id_prefix("120")
        self.set_id_suffix("")
        self.set_used_ids([''])  # (self.get_used_ids_by_other_students()


    def get_used_ids_by_other_students(self):
        from Domain.Department import Department
        all_students = Department.get_instance().get_all_students()
        already_used_ids = [student.get_student_no() for student in all_students]
        return already_used_ids

