package Domain;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testRegisterToNewCourse() {

        Student student = new Student();

        Course course = new Course();

        course.setPrerequisitesIds(Arrays.asList());

        Department.getInstance().getAllCourses().add();

        Advisor advisor = new Advisor();
        Department.getInstance().getAllAdvisors().add();

        student.registerToNewCourse();

        assertTrue(student.getRegistrationNumbers().contains());

        assertTrue(advisor.getRegistrationNumbers().contains());
        
        assertTrue(Department.getInstance().getAllRegistrations().stream().anyMatch(registration -> registration.getRegistrationNo().equals()));
    }
}
