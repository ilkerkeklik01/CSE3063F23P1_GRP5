package Tests;

import Domain.Course;
import Domain.Grade;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GradeTest {

    private Grade grade;

    @Before
    public void setUp() {
        Course course = new Course("Introduction to Computer Science", "CSE1142", null, null, null, null, 3);
        float numericGrade = 90;

        grade = new Grade(course, numericGrade);
    }

    @Test
    public void testConstructorAndGetLetterGrade() {
        assertEquals("AA", grade.getLetterGrade());
    }

    @Test
    public void testIsPassed() {
        assertTrue(grade.isPassed());
        grade.setNumericGrade(40);
        assertFalse(grade.isPassed());
    }
}