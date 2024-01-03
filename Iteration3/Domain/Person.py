from abc import ABC, abstractmethod

class Person(ABC):
    def __init__(self, first_name, last_name, password):
        self.person_id = None
        self.set_first_name(first_name)
        self.set_last_name(last_name)
        self.set_password(password)

    def get_person_id(self):
        return self.person_id

    def set_person_id(self, person_id):
        self.person_id = person_id

    def get_first_name(self):
        return self.first_name

    def set_first_name(self, first_name):
        self.first_name = first_name

    def get_last_name(self):
        return self.last_name

    def set_last_name(self, last_name):
        self.last_name = last_name

    def set_password(self, password):
        self.password = password

    def get_password(self):
        return self.password

    @abstractmethod
    def search(self, query):
        pass

    def __str__(self):
        return f"Person{{person_id='{self.person_id}', first_name='{self.first_name}', last_name='{self.last_name}'}}"
