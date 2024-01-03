from Domain.Lecturer import Lecturer
from Domain.Student import Student
from Domain.RegistrationStatus import RegistrationStatus

class Advisor(Lecturer):
    def __init__(self, FName, LName, staffNo, courseIds, studentNumbers, registrationNumbers):
        super().__init__(FName, LName, staffNo)
        self.course_ids = courseIds
        self.studentNumbers = studentNumbers
        self.registrationNumbers = registrationNumbers

    def get_active_registrations(self):
        from Domain.Department import Department
        registrations = []
        all_registrations = Department.get_instance().get_all_registrations()

        for registration in all_registrations:
            if registration.get_status() == RegistrationStatus.Active and registration.get_advisor_no() == self.get_staff_no():
                registrations.append(registration)

        return registrations

    def is_active_registration(self, registration_no):
        active_registrations = self.get_active_registrations()

        for each in active_registrations:
            if each.get_registration_no() == registration_no:
                return each

        return None

    def proceed_the_registration(self, registration_no, status):
        from Domain.Department import Department

        registration = self.is_active_registration(registration_no)

        if registration is None:
            print(registration_no + " is not an active registration or is not a valid registration")
            return

        registration.set_status(status)

        if status == RegistrationStatus.Confirmed:
            student = Department.get_instance().get_student_by_student_no(registration.get_student_no())
            student.get_course_codes().append(registration.get_course_code())
            course = Department.get_instance().get_course_by_course_code(registration.get_course_code())
            course.get_student_numbers().append(student.get_student_no())
            student.add_course_section(registration.get_course_section())
            course_section = registration.get_course_section()
            course_section.add_student()

            department = Department.get_instance()
            department.get_course_section_by_section_no(registration.get_course_section().get_course_section_no()).add_student()

            sections_of_course = department.get_course_by_course_code(registration.get_course_code()).get_course_sections()
            for section in sections_of_course:
                if section.get_course_section_no() == registration.get_course_section().get_course_section_no():
                    section.add_student()

            print("Registration is Confirmed.")
        else:
            print("Registration is not confirmed")

    def get_student_numbers(self):
        return self.studentNumbers

    def set_student_numbers(self, student_numbers):
        self.studentNumbers = student_numbers

    def get_registration_numbers(self):
        return self.registrationNumbers

    def set_registration_numbers(self, registration_numbers):
        self.registrationNumbers = registration_numbers
    def to_json(self):
        # Convert the Advisor object to a JSON-formatted string
        return json.dumps(self, cls=AdvisorEncoder)


import json
from Domain.Advisor import Advisor

class AdvisorEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, Advisor):
            return {
                'FName': obj.get_first_name(),
                'LName': obj.get_last_name(),
                'staffNo': obj.get_staff_no(),
                'courseIds': obj.get_course_ids(),
                'studentNumbers': obj.get_student_numbers(),
                'registrationNumbers': obj.get_registration_numbers()
            }
        return super().default(obj)
