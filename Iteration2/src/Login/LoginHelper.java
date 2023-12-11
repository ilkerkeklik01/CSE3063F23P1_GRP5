package Login;

import Domain.Course;
import Domain.Department;
import Domain.Lecturer;
import Domain.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class LoginHelper {
    public void printAvailableCourses(Student student){

        Department department = Department.getInstance();
        Collection<Course> allCourses = department.getAllCourses();
        Collection<Course> availableCourses = new ArrayList<>();
        for (Course course : allCourses){
            if(!student.getTranscript().getCompletedCourses().contains(course)){
                availableCourses.add(course);
            }
        }

        for(Course course : availableCourses){
            System.out.println(course);
        }
    }
    public void listCoursesForLecturer(Department department, Lecturer lecturer) {

        short choice = -1;

        while (choice != 0) {
            System.out.println(lecturer.getCourses());
            System.out.println("\n1 to get action with spesific course");
            System.out.println("0 to go back");
            Scanner input2 = null;
            try{
                input2 = new Scanner(System.in);
                choice = input2.nextShort();
            }catch (Exception e){
                System.out.println(e);
            }
            switch (choice) {
                case 1:
                    System.out.println("Enter course code");
                    String courseCode = null;
                    try{
                        courseCode = input2.next();
                    }catch (Exception e){
                        System.out.println(e);
                        break;
                    }

                    Course course = department.getCourseByCourseCode(courseCode);
                    showCourseOptions(course);
                    break;
                case 0:
                    break;


            }
        }



    }
    public  void showCourseOptions(Course course) {
        if (course == null) {
            System.out.println("Course does not found");
            return;
        }

        short choice = -1;

        while (choice != 0) {
            System.out.println(course.getCourseCode() + "--" + course.getCourseName());
            System.out.println("\n1 to list students");
            System.out.println("2 to grade a student");
            System.out.println("0 to go back");
            Scanner input2 = null;
            try{
                input2 = new Scanner(System.in);
                choice = input2.nextShort();
            }catch (Exception e){
                System.out.println(e);
            }

            switch (choice) {
                case 1:
                    System.out.println(course.getStudents());
                    break;
                case 2:
                    System.out.println("Enter the student number to grade student for course:" + course.getCourseCode());
                    String studentNumber = null;
                    try{
                        studentNumber = input2.next();
                    }catch (Exception e){
                        System.out.println(e);
                        break;
                    }

                    Student student = Department.getInstance().getStudentByStudentNo(studentNumber);
                    if (student == null) {
                        break;
                    }
                    gradeStudent(student, course);
                    showCourseOptions(course);
                    break;
                case 0:
                    choice = 0;
                    break;

            }
        }
    }
    public void gradeStudent(Student student, Course course) {

        System.out.println("Enter a grade for student between 0 and 100");
        float grade = 0;
        try{
            Scanner input = new Scanner(System.in);
            grade = input.nextFloat();
        }catch (Exception e){
            System.out.println(e);
            return;
        }
        System.out.println();
        if (grade < 0 && grade > 100) {
            System.out.println("Invalid grade !!");
            return;
        }

        student.getTranscript().addGrade(course.getCourseCode(), grade);

        System.out.println("Student graded.");
    }


}
