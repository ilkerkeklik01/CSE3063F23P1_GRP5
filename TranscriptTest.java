package Domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TranscriptTest {

    @Test
    void testAddGradeAndUpdateTotalCredits() {

        Course course = new Course();

        Department.getInstance().getAllCourses().add();

        Transcript transcript = new Transcript();

        transcript.addGrade();

        List<Grade> grades = transcript.getGrades();

        assertEquals(3, transcript.getCompletedCredits());

        transcript.addGrade();

        assertEquals(3, transcript.getCompletedCredits());
    }

    @Test
    public void testPrintTakenCoursesStatus() {
        Transcript transcript = new Transcript();
        Course course1 = new Course("Course 1", "CSE1142", new ArrayList<>(), null, new ArrayList<>(), new ArrayList<>(), 3);
        Course course2 = new Course("Course 2", "CSE1143", new ArrayList<>(), null, new ArrayList<>(), new ArrayList<>(), 4);
        Department.getInstance().getAllCourses().add(course1);
        Department.getInstance().getAllCourses().add(course2);
        transcript.addGrade(course1.getCourseCode(), 90);
        transcript.addGrade(course2.getCourseCode(), 85);
        transcript.printTakenCoursesStatus();
    }
}
