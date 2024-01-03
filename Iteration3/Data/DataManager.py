import json

from Data.TranscriptDto import TranscriptDto
from Domain.CourseSection import CourseSection
from Domain.Department import Department
from Domain.Registration import Registration
from Domain.Student import Student


class DataManager:
    def __init__(self):
        self.object_mapper = None  # ObjectMapper sınıfının Python karşılığını eklemeniz gerekebilir

        self.COURSES_JSON_FILE = "../courses.json"
        self.REGISTRATIONS_JSON_FILE = "../registrations.json"
        self.STUDENTS_JSON_FILE = "../students.json"
        self.LECTURERS_JSON_FILE = "../lecturers.json"
        self.ADVISORS_JSON_FILE = "../advisors.json"
        self.COURSE_SECTION_JSON_FILE = "../courseSections.json"

    def save_data(self):
        try:
            department = Department.get_instance()  # Department sınıfının Python karşılığını eklemeniz gerekebilir
            with open(self.ADVISORS_JSON_FILE, 'w') as file:
                json.dump(department.get_all_advisors(), file)
            with open(self.COURSES_JSON_FILE, 'w') as file:
                json.dump(department.get_all_courses(), file)
            with open(self.REGISTRATIONS_JSON_FILE, 'w') as file:
                json.dump(department.get_all_registrations(), file)
            with open(self.STUDENTS_JSON_FILE, 'w') as file:
                json.dump(department.get_all_students(), file)
            with open(self.LECTURERS_JSON_FILE, 'w') as file:
                json.dump(department.get_all_lecturers(), file)
            with open(self.COURSE_SECTION_JSON_FILE, 'w') as file:
                json.dump(department.get_all_course_sections(), file)

            self.write_transcript_files()

        except IOError as e:
            print(e)

    def load_data(self):
        try:
            department = Department.get_instance()

            with open(self.ADVISORS_JSON_FILE, 'r', encoding='utf8') as file:
                department.set_all_advisors(json.load(file))
            with open(self.COURSES_JSON_FILE, 'r') as file:
                department.set_all_courses(json.load(file))
            with open(self.REGISTRATIONS_JSON_FILE, 'r') as file:
                department.set_all_registrations([Registration(registration_data['registrationNo'], registration_data['studentNo'], registration_data['advisorNo'], registration_data['courseCode'], registration_data['status'],self.create_course_section(registration_data['courseSection'])) for registration_data in json.load(file)])
            with open(self.STUDENTS_JSON_FILE, 'r', encoding='utf8') as file:
                department.set_all_students([Student(student_data['fname'],student_data['lname'],student_data['studentNo'], student_data['courseCodes'], student_data['advisorNo'], student_data['registrationNumbers'], student_data['semester']) for student_data in json.load(file)])
            with open(self.LECTURERS_JSON_FILE, 'r') as file:
                department.set_all_lecturers(json.load(file))
            with open(self.COURSE_SECTION_JSON_FILE, 'r') as file:
                department.set_all_course_sections(json.load(file))

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
        file_name = f"{student.get_student_no()}.json"
        try:
            with open(file_name, 'w') as file:
                json.dump(self.create_transcript_dto(student), file)
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
        return CourseSection(course_section_data['courseCode'], course_section_data['courseSectionNo'], course_section_data['quota'], course_section_data['sectionTime'])