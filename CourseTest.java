package Tests;

import Domain.Course;
import Domain.Department;
import Domain.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CourseTest {

    private Course course;

    @Before
    public void setUp() {
        String courseName = "Introduction to Computer Science";
        String courseCode = "CSE1142";
        Collection<String> lecturersNumbers = Arrays.asList("1", "2");
        String courseSectionNo = "1";
        Collection<String> studentNumbers = Arrays.asList("150119717", "150119718");
        Collection<String> prerequisitesIds = Arrays.asList("1", "2");
        int credit = 3;

        course = new Course(courseName, courseCode, lecturersNumbers, courseSectionNo, studentNumbers, prerequisitesIds, credit);
        Department.getInstance().getAllCourses().add(course);
        Student student1 = new Student("Sarp", "M", null, "150119717", new ArrayList<>(), "1", new ArrayList<>());
        Student student2 = new Student("Siraç", "M", null, "150119718", new ArrayList<>(), "1", new ArrayList<>());

        Department.getInstance().getAllStudents().addAll(Arrays.asList(student1, student2));
    }

    @Test
    public void testGetStudents() {
        Collection<Student> students = (Collection<Student>) course.getStudents();

        assertNotNull(students);
        assertEquals(2, students.size());
    }
}