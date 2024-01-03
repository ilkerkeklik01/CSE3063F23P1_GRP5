from IDGenerators.IDGenerator import IDGenerator

class RegistrationIDGenerator(IDGenerator):
    def __init__(self):
        super().__init__()
        self.set_digit_count(5)
        self.set_id_prefix("R")
        self.set_id_suffix("")
        self.set_used_ids([''])  # self.get_used_ids_by_other_registrations()


    def get_used_ids_by_other_registrations(self):
        from Domain.Department import Department
        all_registrations = Department.get_instance().get_all_registrations()
        already_used_ids = [registration.get_registration_no() for registration in all_registrations]
        return already_used_ids

