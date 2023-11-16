package Domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AdvisorTest {

    private Advisor advisor;

    @BeforeEach
    void setUp() {
        String fName = "Sarp";
        String lName = "M";
        Date birthdate = new Date();
        String staffNo = "123";
        Collection<String> courseIds = Arrays.asList("001", "002");
        Collection<String> studentNumbers = Arrays.asList("150119717", "150119718");
        Collection<String> registrationNumbers = Arrays.asList("101", "102");

        advisor = new Advisor(fName, lName, birthdate, staffNo, courseIds, studentNumbers, registrationNumbers);
    }

    @Test
    void getActiveRegistrations() {
        assertEquals(0, advisor.getActiveRegistrations().size());
    }

    @Test
    void isActiveRegistration() {
        assertNull(advisor.isActiveRegistration("InvalidRegistrationNo"));
        assertNotNull(advisor.isActiveRegistration("101"));
    }

    @Test
    void proceedTheRegistration() {
        advisor.proceedTheRegistration("101", RegistrationStatus.Confirmed);

        Student student = Department.getInstance().getStudentByStudentNo("150119717");
        assertTrue(student.getCourseCodes().contains("001"));

        Course course = Department.getInstance().getCourseByCourseCode("001");
        assertTrue(course.getStudentNumbers().contains("150119717"));
    }

    @Test
    void setStudentNumbers() {
        Collection<String> newStudentNumbers = Arrays.asList("150119719", "150119720");
        advisor.setStudentNumbers(newStudentNumbers);

        assertEquals(newStudentNumbers, advisor.getStudentNumbers());
    }

    @Test
    void setRegistrationNumbers() {
        Collection<String> newRegistrationNumbers = Arrays.asList("150119719", "150119720");
        advisor.setRegistrationNumbers(newRegistrationNumbers);

        assertEquals(newRegistrationNumbers, advisor.getRegistrationNumbers());
    }
}