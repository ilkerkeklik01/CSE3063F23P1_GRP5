import Domain.*;

import java.util.Scanner;
import Data.*;
import Login.AdvisorLoginStrategy;
import Login.LecturerLoginStrategy;
import Login.LoginContext;
import Login.StudentLoginStrategy;

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Department department = Department.getInstance();

        DataManager dataManager = new DataManager();


        dataManager.loadData();


        System.out.println();

        displayConsoleUI(department);

        dataManager.saveData();


    }


    private static void displayConsoleUI(Department department) {

        DataManager dataManager = new DataManager();
        short choice = -1;

            LoginContext loginContext = new LoginContext();

        while (choice != 0) {

            System.out.println("Press 1 to login as a student");
            System.out.println("Press 2 to login as an lecturer");
            System.out.println("Press 3 to login as an advisor");
            System.out.println("Press 0 to exit!");

            try{
                Scanner input = new Scanner(System.in);
                choice = input.nextShort();
            }catch (Exception e){
                System.out.println(e);
            }
            switch (choice) {
                case 0:
                    dataManager.saveData();
                    System.exit(0);
                    break;
                case 1://login as a student
                    loginContext.setStrategy(new StudentLoginStrategy());
                    loginContext.login(department);
                    break;
                case 2://login as an advisor
                    loginContext.setStrategy(new LecturerLoginStrategy());
                    loginContext.login(department);
                    break;
                case 3:
                    loginContext.setStrategy(new AdvisorLoginStrategy());
                    loginContext.login(department);
                    break;
                default://default case
                    break;
            }
        }//While loop end
    }//displayConsoleUI end














/*
    public static void getEverything(Department department) {
        System.out.println("\nGet all people\n");
        for (Person person : department.getAllPeople()) {
            System.out.println(person.getClass().getName() + " " + person.getFName() + " " + person.getLName());
        }
        System.out.println("\nGet all Students\n");
        for (Student student : department.getAllStudents()) {
            System.out.println(student.getClass().getName() + " " + student.getFName() + " " + student.getLName());
        }
        System.out.println("\nGet all Advisors\n");
        for (Advisor advisor : department.getAllAdvisors()) {
            System.out.println(advisor.getClass().getName() + " " + advisor.getFName() + " " + advisor.getLName());
        }
        System.out.println("\nGet all Courses\n");
        for (Course course : department.getAllCourses()) {
            System.out.println(course.getClass().getName() + " " + course.getCourseCode() + " " + course.getCourseName());
        }
        System.out.println("\nGet all Registrations\n");
        for (Registration registration : department.getAllRegistrations()) {
            System.out.println(registration);
        }
    }
    */
/*public static void testMethod(Department department1){

        //COURSES

        addCourses(department1);






        IDGenerator idGenerator = new IDGenerator();
        Advisor advisor = new Advisor("Ilker","Keklik", null, idGenerator.generateNewID(IDType.StaffID),new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>());

        advisor.addTeachingCourse("CSE3215");
        advisor.addTeachingCourse("CSE3033");
        advisor.addTeachingCourse("CSE3055");
        advisor.addTeachingCourse("CSE3038");
        advisor.addTeachingCourse("CSE3044");
        advisor.addTeachingCourse("CSE3048");


        department1.addAnObject(advisor);
        Advisor advisor2 = new Advisor("Burkay","Keklik", null,idGenerator.generateNewID(IDType.StaffID),new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>());

        advisor2.addTeachingCourse("CSE3063");
        advisor2.addTeachingCourse("IE3081");
        advisor2.addTeachingCourse("CSE3264");
        advisor2.addTeachingCourse("IE3235");
        advisor2.addTeachingCourse("CSE2202");

        department1.addAnObject(advisor2);





        System.out.println();
        student.getTranscript().printTakenCoursesStatus();

        //System.out.println("Before proceed:"+student.getCourseCodes());
        //System.out.println("After proceed:"+student.getCourseCodes());
        //System.out.println("After2 proceed:"+student2.getCourseCodes());
        System.out.println(advisor.getActiveRegistrations());
    }





*/
}

