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
    void testPrintTakenCoursesStatus() {

        Transcript transcript = new Transcript();

        transcript.addGrade();

        transcript.addGrade();

        // Need something to read the inputs

        transcript.printTakenCoursesStatus();

        // Need something for system to reset itself for the next student

        // Need something to compare expected vs given output
    }
}
