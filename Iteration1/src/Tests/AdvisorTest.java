package Tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import Domain.Advisor;
import org.junit.Before;
import org.junit.Test;


public class AdvisorTest {

    private Advisor advisor;

    @Before
    public void setUp() {
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
    public void getActiveRegistrations() {
        assertEquals(0, advisor.getActiveRegistrations().size());
    }


   /* @Test
    public void isActiveRegistration() {
        assertNull(advisor.isActiveRegistration("InvalidRegistrationNo"));
        assertNotNull(advisor.isActiveRegistration("101"));
    }

    @Test
    public void proceedTheRegistration() {
        advisor.proceedTheRegistration("101", RegistrationStatus.Confirmed);

        Student student = Department.getInstance().getStudentByStudentNo("150119717");
        assertTrue(student.getCourseCodes().contains("001"));

        Course course = Department.getInstance().getCourseByCourseCode("001");
        assertTrue(course.getStudentNumbers().contains("150119717"));
    }*/

    @Test
    public void setStudentNumbers() {
        Collection<String> newStudentNumbers = Arrays.asList("150119719", "150119720");
        advisor.setStudentNumbers(newStudentNumbers);

        assertEquals(newStudentNumbers, advisor.getStudentNumbers());
    }


    @Test
    public void setRegistrationNumbers() {
        Collection<String> newRegistrationNumbers = Arrays.asList("150119719", "150119720");
        advisor.setRegistrationNumbers(newRegistrationNumbers);

        assertEquals(newRegistrationNumbers, advisor.getRegistrationNumbers());
    }
}