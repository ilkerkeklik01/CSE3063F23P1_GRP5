from IDGenerators.IDGenerator import IDGenerator
import random

class StaffIDGenerator(IDGenerator):
    def __init__(self):
        super().__init__()
        self.set_digit_count(8)
        self.set_id_prefix("100")
        self.set_id_suffix("")
        self.set_used_ids(['']) # (self.get_used_ids_by_other_staffs())


    def get_used_ids_by_other_staffs(self):
        from Domain.Department import Department
        all_staff = Department.get_instance().get_all_staffs()
        already_used_ids = [staff.get_staff_no() for staff in all_staff]
        return already_used_ids

