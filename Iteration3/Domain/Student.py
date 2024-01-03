from Domain.Person import Person
from Domain.Registration import Registration
from Domain.Transcript import Transcript
from Domain.RegistrationStatus import RegistrationStatus

class Student(Person):
    def __init__(self, FName, LName, studentNo, courseCodes, advisorNo, registrationNumbers, semester):
        super().__init__(FName, LName)
        self.studentNo = studentNo
        self.courseCodes = courseCodes
        self.advisorNo = advisorNo
        self.registrationNumbers = registrationNumbers
        self.transcript = Transcript()
        self.semester = semester
        self.activeCourseSections = []



    def add_course_section(self, course_section):
        if self.activeCourseSections is None:
            self.activeCourseSections = []
        self.activeCourseSections.append(course_section)

    def course_null_check(self, course, course_code):
        if course is None:
            raise Exception("There is no such a course: " + course_code)

    def prerequisites_control(self, course):
        if not set(self.transcript.get_passed_course_codes()).issuperset(set(course.get_prerequisites_ids())):
            raise Exception(
                "You have to pass the prerequisites of the course: " + course.get_course_code() +
                "\nPrerequisites: " + course.get_prerequisites_ids()
            )

    def cannot_take_more_than_five_courses(self):
        if self.student_has_5_or_more_courses():
            raise Exception("You can not take more than 5 courses.\nEither you selected more than 5 courses or "
                            "the number of active registrations and your courses count is more than 5.")

    def cannot_take_more_than_36_credits(self, course):
        total_credit = self.calculate_total_credit_taken() + course.get_credit()
        if total_credit > 36:
            raise Exception("You cannot take more than 36 credits in one semester.")

    def check_eligibility_for_graduation_project(self, asked_course):
        if asked_course.get_course_code() == "CSE4074":
            total_credit = 0
            min_credit_for_graduation_project = 165

            for course in self.transcript.get_completed_courses():
                if course.get_semester() < 4:
                    total_credit += course.get_credit()

            if total_credit < min_credit_for_graduation_project:
                raise Exception("You cannot take graduation project. Your credits for first 6 semesters are below "
                                + str(min_credit_for_graduation_project))

    def calculate_total_credit_taken(self):
        from Domain.Department import Department
        department = Department.get_instance()
        total_credit_taken = 0
        for course_code in self.courseCodes:
            course = department.get_course_by_course_code(course_code)
            total_credit_taken += course.get_credit()
        return total_credit_taken

    def course_already_completed_check(self, course):
        if course.get_course_code() in self.transcript.get_successfully_completed_course_codes():
            raise Exception("You have already completed this course: " + course.get_course_code())

    def check_if_eligible_for_upper_class(self, course):
        min_gano_for_upper_class = 3
        if course.get_semester() > self.semester and self.transcript.get_gano() < min_gano_for_upper_class:
            raise Exception("You are not eligible for upper class. Your GANO is less than " + str(min_gano_for_upper_class))

    def course_currently_taken_check(self, course):
        if course.get_course_code() in self.courseCodes:
            raise Exception("You are currently taking this course: " + course.get_course_code())

    def check_course_eligibility(self, course_code):
        from Domain.Department import Department

        course = Department.get_instance().get_course_by_course_code(course_code)
        self.course_null_check(course, course_code)
        self.course_currently_taken_check(course)
        self.course_already_completed_check(course)
        self.cannot_take_more_than_five_courses()
        self.cannot_take_more_than_36_credits(course)
        self.check_if_eligible_for_upper_class(course)
        self.prerequisites_control(course)
        self.check_eligibility_for_graduation_project(course)

    def check_overlap_sections(self, course, section_no):
        from Domain.Department import Department

        course_section = Department.get_instance().get_course_section_by_section_no(section_no)
        if self.activeCourseSections is None:
            return
        for each in self.get_active_course_sections():
            for each_time in each.get_section_times():
                if each_time in course_section.get_section_times():
                    raise Exception("You have an overlap with the course: " + course.get_course_code() +
                                    " and the course: " + each.get_course_code())

    def check_quota(self, course, section_no):
        from Domain.Department import Department

        course_section = Department.get_instance().get_course_section_by_section_no(section_no)
        if course_section.is_full():
            raise Exception("The course section is full: " + course.get_course_code() + " " +
                            course_section.get_course_section_no())


    def check_course_section_eligibility(self, course, sectionNo):
        self.check_overlap_sections(course, sectionNo)
        self.check_quota(course, sectionNo)

    def register_to_new_course(self, courseCode, new_registration_no, sectionNo):
        from Domain.Department import Department

        department = Department.get_instance()
        course = department.get_course_by_course_code(courseCode)

        try:
            courseSection = department.get_course_section_by_section_no(sectionNo)
            self.check_course_section_eligibility(course, sectionNo)

            registration = Registration(new_registration_no, self.studentNo, self.advisorNo, courseCode,
                                        RegistrationStatus.Active, courseSection)
            self.registrationNumbers.append(registration.get_registration_no())
            advisor = Department.get_instance().get_advisor_by_staff_no(self.advisorNo)
            advisor.get_registration_numbers().append(registration.get_registration_no())
            department.get_all_registrations().append(registration)

            print("Registration is successfully sent to the advisor")

        except Exception as e:
            print(e)

    def set_semester(self, semester):
        self.semester = semester

    def get_semester(self):
        return self.semester

    def get_transcript(self):
        return self.transcript

    def get_available_courses(self):
        from Domain.Department import Department

        department = Department.get_instance()
        all_courses = department.get_all_courses()
        available_courses = []
        for course in all_courses:
            if course.get_course_code() not in self.transcript.get_successfully_completed_course_codes():
                available_courses.append(course)
        return available_courses

    def get_registration_numbers(self):
        if(self.registrationNumbers == None):
            self.registrationNumbers = []
        return self.registrationNumbers

    def set_registration_numbers(self, registration_numbers):
        self.registration_numbers = registration_numbers

    def get_student_no(self):
        return self.studentNo

    def set_student_no(self, student_no):
        self.studentNo = student_no

    def get_course_codes(self):
        if(self.courseCodes == None):
            self.courseCodes = []
        return self.courseCodes

    def set_course_codes(self, course_codes):
        self.courseCodes = course_codes

    def get_advisor_no(self):
        return self.advisorNo

    def set_advisor_no(self, advisor_no):
        self.advisorNo = advisor_no

    def get_registrations(self):
        from Domain.Department import Department

        return [r for r in Department.get_instance().get_all_registrations() if r.get_student_no() == self.studentNo]

    def student_has_5_or_more_courses(self):
        return len(self.courseCodes) + \
               len([r for r in self.get_registrations() if r.get_status() == RegistrationStatus.Active]) >= 5

    def get_active_course_sections(self):
        return self.activeCourseSections

    def set_active_course_sections(self, activeCourseSections):
        self.activeCourseSections = activeCourseSections

    def __str__(self):
        return "Student{" + \
               "studentNo='" + self.studentNo + '\'' + \
               ", courseCodes=" + str(self.courseCodes) + \
               ", advisorNo='" + self.advisorNo + '\'' + \
               ", registrationNumbers=" + str(self.get_registration_numbers()) + \
               "} "

    def search(self, query):
        # Your implementation for the search method in the Student class...
        pass


# You can add other classes and methods here as needed.
import json
from Domain.Student import Student  # Add this import statement
from Domain.CourseSection import CourseSectionEncoder
from Domain.Transcript import TranscriptEncoder

class StudentEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, Student):
            return {
                'FName': obj.get_first_name(),
                'LName': obj.get_last_name(),
                'studentNo': obj.get_student_no(),
                'courseCodes': obj.get_course_codes(),
                'advisorNo': obj.get_advisor_no(),
                'registrationNumbers': obj.get_registration_numbers(),
                'semester': obj.get_semester(),
                'transcript': TranscriptEncoder().default(obj.get_transcript()),
                'activeCourseSections': [CourseSectionEncoder().default(section) for section in obj.get_active_course_sections()]
            }
        return super().default(obj)
