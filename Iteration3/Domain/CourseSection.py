from typing import List

class CourseSection:
    def __init__(self, course_code, course_section_no, quota, section_times):
        self.no_of_students = 0
        self.course_code = course_code
        self.course_section_no = course_section_no
        self.quota = quota
        self.section_times = section_times


    def add_student(self):
        self.no_of_students += 1

    def print_sections(self):
        print(f"Course Section No: {self.course_section_no} Quota: {self.quota} No of Students: {self.no_of_students} Lecture Times:")
        for time in self.section_times:
            print(f"{self.get_day(time)} {time[1:3]}:00")
        print()

    def is_full(self):
        return self.no_of_students == self.quota

    #region Properties

    def get_course_code(self):
        return self.course_code

    def set_course_code(self, course_code):
        self.course_code = course_code

    def get_course_section_no(self):
        return self.course_section_no

    def set_course_section_no(self, course_section_no):
        self.course_section_no = course_section_no

    def get_no_of_students(self):
        return self.no_of_students

    def set_no_of_students(self, no_of_students):
        self.no_of_students = no_of_students

    def get_quota(self):
        return self.quota

    def set_quota(self, quota):
        self.quota = quota

    def get_section_times(self):
        return self.section_times

    def set_section_times(self, section_times):
        self.section_times = section_times
    #endregion Properties

    def get_day(self, time):
        day_no = time[0]
        days = {
            "1": "Monday",
            "2": "Tuesday",
            "3": "Wednesday",
            "4": "Thursday",
            "5": "Friday"
        }
        return days.get(day_no, "Free Day")


import json
from Domain.CourseSection import CourseSection

class CourseSectionEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, CourseSection):
            return {
                'course_code': obj.get_course_code(),
                'course_section_no': obj.get_course_section_no(),
                'quota': obj.get_quota(),
                'section_times': obj.get_section_times()
            }
        return super().default(obj)
