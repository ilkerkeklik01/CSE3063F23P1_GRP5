from IDGenerators.IDGenerator import IDGenerator

class PersonIDGenerator(IDGenerator):
    def __init__(self):
        super().__init__()
        self.set_digit_count(8)
        self.set_id_prefix("999")
        self.set_id_suffix("")
        self.set_used_ids(['']) # self.get_used_ids_by_other_people()

'''
    def get_used_ids_by_other_people(self):
        all_persons = Department.get_instance().get_all_people()
        already_used_ids = [person.get_person_id() for person in all_persons]
        return already_used_ids
'''