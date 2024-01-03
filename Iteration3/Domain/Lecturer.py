from typing import Collection

from Domain.Staff import Staff

class Lecturer(Staff):
    def __init__(self, first_name, last_name, staff_no):
        super().__init__(first_name, last_name, staff_no)
        self.course_ids = []  # Initialize an empty list for course_ids

    def search(self, query):
        # Placeholder implementation for the abstract search method
        # Add your search logic here
        pass

    def get_course_ids(self):
        return self.course_ids

    def set_courses(self, course_ids: Collection[str]):
        self.course_ids = course_ids

    def add_teaching_course(self, course_id):
        self.course_ids.append(course_id)

    def get_courses(self):
        from Domain.Department import Department
        courses = []
        all_courses = Department.get_instance().get_all_courses()

        for course in all_courses:
            if course.get_course_code() in self.get_course_ids():
                courses.append(course)

        return courses

    def __str__(self):
        return f"Lecturer{{}} {super().__str__()}"
