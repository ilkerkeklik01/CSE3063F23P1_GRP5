package Tests;

import Domain.Advisor;
import Domain.Course;
import Domain.Department;
import Domain.Student;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class StudentTest {


    @Test
    public void testRegisterToNewCourse() {

        ArrayList<String> courses = new ArrayList<>();
        courses.add("CSE1000");

        ArrayList<String> registrationNos = new ArrayList<>();
        registrationNos.add("100");

        ArrayList<String> lecturerNos = new ArrayList<>();
        registrationNos.add("200");

        ArrayList<String> studentNos = new ArrayList<>();
        registrationNos.add("123");

        ArrayList<String> coursesNos =new ArrayList<String>();
        coursesNos.add("CSE1000");


        Advisor advisor = new Advisor("","",null,"2",coursesNos
                ,new ArrayList<>(),registrationNos);
        Department.getInstance().addAnObject(advisor);

        Student student = new Student("abc","def",null,"123",courses,"2",registrationNos,1);

        Course course = new Course("course","CSE1000",lecturerNos,null,studentNos,null,2);

        course.setPrerequisitesIds(Arrays.asList());

        Department.getInstance().getAllCourses().add(course);


        Department.getInstance().getAllAdvisors().add(advisor);

        student.registerToNewCourse(course.getCourseCode(),"200","S23");

        assertTrue(student.getRegistrationNumbers().contains("200"));

        assertTrue(advisor.getRegistrationNumbers().contains("200"));

    }

}