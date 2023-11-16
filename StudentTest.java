package Domain;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testRegisterToNewCourse() {

        Student student = new Student(DOLDUR);

        Course course = new Course(DOLDUR);

        course.setPrerequisitesIds(Arrays.asList(yukarda course a girdiğiniz kodu girin));

        Department.getInstance().getAllCourses().add(DOLDUR, course);

        Advisor advisor = new Advisor(DOLDUR);
        Department.getInstance().getAllAdvisors().add( DOLDUR ,advisor);

        student.registerToNewCourse(DOLDUR);

        assertTrue(student.getRegistrationNumbers().contains(DOLDUR));

        assertTrue(advisor.getRegistrationNumbers().contains(DOLDUR));
        
        assertTrue(Department.getInstance().getAllRegistrations().stream().anyMatch(registration -> registration.getRegistrationNo().equals(DOLDUR)));
    }
}