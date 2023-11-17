package Domain;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class TranscriptTest {

    @Test
    public void testAddGradeAndUpdateTotalCredits() {

        Course course = new Course();

        Department.getInstance().getAllCourses().add(course);

        Transcript transcript = new Transcript();

        transcript.addGrade(course.getCourseCode(),100);

        List<Grade> grades = transcript.getGrades();

        assertEquals(3, transcript.getCompletedCredits());

        transcript.addGrade(course.getCourseCode(), 100);

        assertEquals(3, transcript.getCompletedCredits());
    }

    //@Test
    //void testPrintTakenCoursesStatus() {
//
    //    Transcript transcript = new Transcript();
//
    //    transcript.addGrade();
//
    //    transcript.addGrade();
//
    //    // Need something to read the inputs
//
    //    transcript.printTakenCoursesStatus();
//
    //    // Need something for system to reset itself for the next student
//
    //    // Need something to compare expected vs given output
    //}
}