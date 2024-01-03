class GradeDto:
    def __init__(self, course_code, num_grade):
        self.courseCode = course_code
        self.numGrade = num_grade


class TranscriptDto:
    def __init__(self, student=None):
        if student:
            self.studentNo = student.get_student_no()
            transcript = student.get_transcript()
            self.completedCredits = transcript.get_completed_credits()
            self.grades = [GradeDto(grade.course_code, grade.num_grade) for grade in transcript.get_grades()]
        else:
            self.studentNo = None
            self.completedCredits = 0
            self.grades = []