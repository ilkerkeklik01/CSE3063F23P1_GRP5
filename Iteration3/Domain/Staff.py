from abc import ABC
from Domain.Person import Person

class Staff(Person, ABC):
    def __init__(self, first_name, last_name, staff_no):
        super().__init__(first_name, last_name)
        self.set_staff_no(staff_no)

    def get_staff_no(self):
        return self.staff_no

    def set_staff_no(self, staff_no):
        self.staff_no = staff_no

    def __str__(self):
        return f"Staff{{staff_no='{self.staff_no}'}} {super().__str__()}"
