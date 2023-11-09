import Domain.*;

import java.util.Scanner;

public class CourseRegistrationSystem {
    public static void main(String[] args) {

        System.out.println("\nGet all people\n");
        for(Person person : Department.getAllPeople()){
            System.out.println(person.getClass().getName()+" "+person.getFName()+" "+person.getLName());
        }
        System.out.println("\nGet all Students\n");
        for(Student student : Department.getAllStudents()){
            System.out.println(student.getClass().getName()+" "+student.getFName()+" "+student.getLName());
        }
        System.out.println("\nGet all Advisors\n");
        for(Advisor advisor : Department.getAllAdvisors()){
            System.out.println(advisor.getClass().getName()+" "+advisor.getFName()+" "+advisor.getLName());
        }
        System.out.println("\nGet all Courses\n");
        for(Course course : Department.getAllCourses()){
            System.out.println(course.getClass().getName()+" "+course.getCourseCode()+" "+course.getCourseName());
        }
        System.out.println("\nGet all Registrations\n");
        for(Registration registration : Department.getAllRegistrations()){
            System.out.println(registration);
        }

        System.out.println();

        displayConsoleUI();
    }

    public static void displayConsoleUI(){
        short choice = -1;
        System.out.println("Press 1 to add a Student");
        System.out.println("Press 2 to add an Advisor");
        System.out.println("Press 3 to add a Lecturer");
        System.out.println("Press 4 to add a Course");
        System.out.println("Press 5 to login as a student");
        System.out.println("Press 6 to login as an advisor");
        System.out.println("Press 0 to exit!");
        while(choice!=0){
            Scanner input = new Scanner(System.in);
            choice = input.nextShort();
            switch (choice){
                case 0:
                    System.exit(0);
                    break;
                case 1://add a Student
                    break;
                case 2://add an Advisor
                    break;
                case 3://add a Lecturer
                    break;
                case 4://add a Course
                    break;
                case 5://login as a student
                    break;
                case 6://login as an advisor
                    break;
                default://default case
                    break;
            }
        }//While loop end
    }//displayConsoleUI end


}
