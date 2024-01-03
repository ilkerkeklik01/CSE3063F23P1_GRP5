import json

from Data.TranscriptDto import TranscriptDto
from Domain.Advisor import Advisor
from Domain.Course import Course
from Domain.CourseSection import CourseSection
from Domain.Department import Department
from Domain.Lecturer import Lecturer
from Domain.Registration import Registration
from Domain.Student import Student


class DataManager:
    def __init__(self):
        self.object_mapper = None  # ObjectMapper sınıfının Python karşılığını eklemeniz gerekebilir

        self.COURSES_JSON_FILE = "courses.json"
        self.REGISTRATIONS_JSON_FILE = "registrations.json"
        self.STUDENTS_JSON_FILE = "students.json"
        self.LECTURERS_JSON_FILE = "lecturers.json"
        self.ADVISORS_JSON_FILE = "advisors.json"
        self.COURSE_SECTION_JSON_FILE = "courseSections.json"

    def save_data(self):
        try:
            from Domain.Advisor import AdvisorEncoder
            from Domain.Registration import RegistrationEncoder
            from Domain.Course import CourseEncoder
            from Domain.CourseSection import CourseSectionEncoder
            from Domain.Lecturer import LecturerEncoder
            from Domain.Student import StudentEncoder

            department = Department.get_instance()

            advisors = department.get_all_advisors()
            if advisors:
                with open(self.ADVISORS_JSON_FILE, 'w') as file:
                    json.dump(advisors, file, cls=AdvisorEncoder)

            courses = department.get_all_courses()
            if courses:
                with open(self.COURSES_JSON_FILE, 'w') as file:
                    json.dump(courses, file, cls=CourseEncoder)

            registrations = department.get_all_registrations()
            if registrations:
                with open(self.REGISTRATIONS_JSON_FILE, 'w') as file:
                    json.dump(registrations, file, cls=RegistrationEncoder)


            lecturers = department.get_all_lecturers()
            if lecturers:
                with open(self.LECTURERS_JSON_FILE, 'w') as file:
                    json.dump(lecturers, file, cls=LecturerEncoder)

            course_sections = department.get_all_course_sections()
            if course_sections:
                with open(self.COURSE_SECTION_JSON_FILE, 'w') as file:
                    json.dump(course_sections, file, cls=CourseSectionEncoder)

            students = department.get_all_students()  # Implement this method to get all students
            if students:
                with open(self.STUDENTS_JSON_FILE, 'w') as file:
                    json.dump(students, file, cls=StudentEncoder)

            self.write_transcript_files()

        except IOError as e:
            print(f"Error saving data: {e}")

    def load_data(self):
        try:
            department = Department.get_instance()

            with open(self.ADVISORS_JSON_FILE, 'r', encoding='utf8') as file:
                department.set_all_advisors([Advisor(advisor_data['FName'], advisor_data['LName'],
                                                     advisor_data['staffNo'], advisor_data['courseIds'],
                                                     advisor_data['studentNumbers'],
                                                     advisor_data['registrationNumbers'], "12345") for advisor_data in
                                             json.load(file)])
            with open(self.COURSES_JSON_FILE, 'r', encoding='utf8') as file:
                department.set_all_courses([Course(course_data['course_name'], course_data['course_code'],
                                                   course_data['lecturers_numbers'],
                                                   self.create_course_section_list(course_data['course_section']),
                                                   course_data['student_numbers'], course_data['prerequisites_ids'],
                                                   course_data['credit'], course_data['semester']) for course_data in json.load(file)])
            with open(self.REGISTRATIONS_JSON_FILE, 'r', encoding='utf8') as file:
                department.set_all_registrations([Registration(registration_data['registration_no'],
                                                               registration_data['student_no'],
                                                               registration_data['advisor_no'],
                                                               registration_data['course_code'],
                                                               registration_data['status'], self.create_course_section(
                        registration_data['course_section'])) for registration_data in json.load(file)])
            with open(self.STUDENTS_JSON_FILE, 'r', encoding='utf8') as file:
                department.set_all_students([Student(student_data['FName'], student_data['LName'],
                                                     student_data['studentNo'], student_data['courseCodes'],
                                                     student_data['advisorNo'], student_data['registrationNumbers'],
                                                     student_data['semester'],"12345") for student_data in json.load(file)])
            with open(self.LECTURERS_JSON_FILE, 'r', encoding='utf8') as file:
                department.set_all_lecturers([Lecturer(lecturer_data['first_name'], lecturer_data['last_name'],
                                                       lecturer_data['staff_no'], lecturer_data['course_ids'],"12345") for
                                              lecturer_data in json.load(file)])
            with open(self.COURSE_SECTION_JSON_FILE, 'r', encoding='utf8') as file:
                department.set_all_course_sections([CourseSection(course_section_data['course_code'],
                                                                  course_section_data['course_section_no'],
                                                                  course_section_data['quota'],
                                                                  course_section_data['section_times'],
                                                                  course_section_data['no_of_students']) for
                                                    course_section_data in json.load(file)])

            self.read_transcript_files()

        except IOError as e:
            print(e)

        self.load_data_to_abstracts()

    def load_data_to_abstracts(self):
        department = Department.get_instance()

        all_staffs = []
        all_staffs.extend(department.get_all_lecturers())
        department.set_all_staffs(all_staffs)

        all_people = []
        all_people.extend(department.get_all_students())
        all_people.extend(department.get_all_staffs())
        department.set_all_people(all_people)

    def write_transcript_files(self):
        department = Department.get_instance()
        students = department.get_all_students()

        for student in students:
            self.write_transcript_file_for_student(student)

    def write_transcript_file_for_student(self, student):
        from Domain.Student import StudentEncoder
        file_name = f"..\\{student.get_student_no()}.json"
        try:
            with open(file_name, 'w') as file:
                # Use the StudentEncoder to serialize the student object
                json.dump(student, file, cls=StudentEncoder)
        except IOError as e:
            print(e)

    def create_transcript_dto(self, student):
        # TranscriptDto sınıfının Python karşılığını eklemeniz gerekebilir
        return TranscriptDto(student)

    def read_transcript_files(self):
        department = Department.get_instance()
        students = department.get_all_students()

        for student in students:
            self.read_transcript_file_for_student(student)

    def read_transcript_file_for_student(self, student):
        file_name = f"{student.get_student_no()}.json"
        try:
            with open(file_name, 'r') as file:
                transcript_dto = json.load(file)
                self.create_transcript_for_student(student, transcript_dto)
        except IOError as e:
            print(e)

    def create_transcript_for_student(self, student, transcript_dto):
        transcript = student.get_transcript()
        for grade_dto in transcript_dto['grades']:
            transcript.add_grade(grade_dto['courseCode'], grade_dto['numGrade'])

    def create_course_section(self, course_section_data):
        return CourseSection(course_section_data['course_code'], course_section_data['course_section_no'], course_section_data['quota'], course_section_data['section_times'], course_section_data['no_of_students'])

    def create_course_section_list(self, course_section_list_data):
        return [CourseSection(course_section_data['course_code'], course_section_data['course_section_no'],
                             course_section_data['quota'], course_section_data['section_times'], course_section_data['no_of_students']) for course_section_data in course_section_list_data]