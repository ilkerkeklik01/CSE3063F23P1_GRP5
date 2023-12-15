import Domain.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
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

        /* COURSE SECTION ADDITION TO SPECIFIC SEMESTER COURSES
        ArrayList<String> times = new ArrayList<String>();
        times.add("109");
        times.add("110");
        times.add("111");
        times.add("112");
        times.add("113");
        times.add("114");
        times.add("115");
        times.add("116");
        times.add("117");
        times.add("209");
        times.add("210");
        times.add("211");
        times.add("212");
        times.add("213");
        times.add("214");
        times.add("215");
        times.add("216");
        times.add("217");
        times.add("309");
        times.add("310");
        times.add("311");
        times.add("312");
        times.add("313");
        times.add("314");
        times.add("315");
        times.add("316");
        times.add("317");
        times.add("409");
        times.add("410");
        times.add("411");
        times.add("412");
        times.add("413");
        times.add("414");
        times.add("415");
        times.add("416");
        times.add("417");
        times.add("509");
        times.add("510");
        times.add("511");
        times.add("512");
        times.add("513");
        times.add("514");
        times.add("515");
        times.add("516");
        times.add("517");
        int sectionNo = 79;
        int quota = 40;
        int a = 45;

        for(Course course: department.getAllCourses()){

            if(course.getSemester()==7){


                for(int j = 0; j <2 ; j++){
                // create random int 0 40 numbers
                    Random rand = new Random();
                    int random = rand.nextInt(a);
                    ArrayList<String> time = new ArrayList<String>();
                    for(int i = 0 ; i < 2 ; i++){
                        time.add(times.get(random));
                        times.remove(times.get(random));
                        random = rand.nextInt(--a);
                    }
                    
                    CourseSection courseSection = new CourseSection(course.getCourseCode(), String.valueOf(sectionNo), quota, time);
                    sectionNo++;
                    course.AddSection(courseSection);
                }
            }   
        }*/


        /* Add all courses' course sections to the allCourseSections collection
        for(Course course: department.getAllCourses()){

            if(course.getCourseSection()!=null){
                for(CourseSection courseSection: course.getCourseSection()){

                    System.out.println(courseSection.getCourseSectionNo());
                    System.out.println(courseSection.getcourseCode());
                    System.out.println(courseSection.getSectionTime());
                    department.addAnObject(courseSection);
                }
            
            }
        }*/

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

