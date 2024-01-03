class Registration:
    def __init__(self, registration_no, student_no, advisor_no, course_code, status, course_section):
        self.registration_no = registration_no
        self.student_no = student_no
        self.advisor_no = advisor_no
        self.course_code = course_code
        self.status = status
        self.course_section = course_section

    # region Properties

    def get_registration_no(self):
        return self.registration_no

    def set_registration_no(self, registration_no):
        self.registration_no = registration_no

    def get_student_no(self):
        return self.student_no

    def set_student_no(self, student_no):
        self.student_no = student_no

    def get_advisor_no(self):
        return self.advisor_no

    def set_advisor_no(self, advisor_no):
        self.advisor_no = advisor_no

    def get_course_code(self):
        return self.course_code

    def set_course_code(self, course_code):
        self.course_code = course_code

    def get_status(self):
        return self.status

    def set_status(self, status):
        self.status = status

    def get_course_section(self):
        return self.course_section

    def set_course_section(self, course_section):
        self.course_section = course_section
    # endregion Properties

    def __str__(self):
        return f"Registration{{registration_no='{self.registration_no}', student_no='{self.student_no}', " \
               f"advisor_no='{self.advisor_no}', course_code='{self.course_code}', status={self.status}}}"



import json
from Domain.CourseSection import CourseSectionEncoder
from Domain.RegistrationStatus import RegistrationStatusEncoder

class RegistrationEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, Registration):
            return {
                'registration_no': obj.get_registration_no(),
                'student_no': obj.get_student_no(),
                'advisor_no': obj.get_advisor_no(),
                'course_code': obj.get_course_code(),
                'status': RegistrationStatusEncoder().default(obj.get_status()),
                'course_section': CourseSectionEncoder().default(obj.get_course_section()),
            }
        return super().default(obj)
