from Domain.Department import Department
import logging

class LoginHelper:

    def print_available_courses(self, student):
        department = Department.get_instance()
        all_courses = department.get_all_courses()
        available_courses = [course for course in all_courses if
                             course not in student.get_transcript().get_completed_courses()]

        for course in available_courses:
            print(course)

    def list_courses_for_lecturer(self, department, lecturer):
        choice = -1

        while choice != 0:
            for course in lecturer.get_courses():
                print(course)
            print("\n1 to get action with specific course")
            print("0 to go back")

            try:
                choice = int(input())
            except ValueError as e:
                print("You should enter either 0 or 1")
                continue

            if choice == 1:
                print("Enter course code")
                course_code = input()

                course = department.get_course_by_course_code(course_code)
                if course is  None:
                    print(f"Course {course_code} does not found")
                else:
                    self.show_course_options(course)
            elif choice == 0:
                break
            else:
                print("You should enter either 0 or 1")


    def show_course_options(self, course):


        choice = -1

        while choice != 0:
            print(course.get_course_code() + "--" + course.get_course_name())
            print("\n1 to list students")
            print("2 to grade a student")
            print("0 to go back")

            try:
                choice = int(input())
            except ValueError as e:
                print(e)

            if choice == 1:
                for student in course.get_students():
                    print(student.listStudents())
            elif choice == 2:
                print("Enter the student number to grade student for course:" + course.get_course_code())
                student_number = input()

                student = Department.get_instance().get_student_by_student_no(student_number)
                if student is None:
                    break
                self.try_to_grade_student(course, student)
            elif choice == 0:
                break

    def try_to_grade_student(self, course, student):
        if course.get_course_code() in student.get_course_codes():
            self.grade_student(student, course)
        else:
            logger = logging.getLogger()
            logger.info(f"Student with number {student.get_student_no()} couldn't not graded  because  is not taking {course.get_course_code()}")
            print(f"Specified student with number {student.get_student_no()} is not taking {course.get_course_code()}")

    def grade_student(self, student, course):
        print("Enter a grade for student between 0 and 100")
        try:
            grade = float(input())
        except ValueError as e:
            print(e)
            return

        if 0 <= grade <= 100:
            student.get_transcript().add_grade(course.get_course_code(), grade)
            logger = logging.getLogger()
            logger.info(f"Student with number {student.get_student_no()}  graded  for {course.get_course_code()}. Grade is {grade}")

            print("Student graded.")
        else:
            print("Invalid grade!!")
