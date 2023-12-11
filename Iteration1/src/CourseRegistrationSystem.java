import Domain.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import Data.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Department department = Department.getInstance();

        DataManager dataManager = new DataManager();


        dataManager.loadData();

        //testMethod(department);
        //getEverything(department);


        System.out.println();

        displayConsoleUI(department);

        dataManager.saveData();


    }


    private static void displayConsoleUI(Department department) {

        DataManager dataManager = new DataManager();
        short choice = -1;

        while (choice != 0) {

            System.out.println("Press 1 to login as a student");
            System.out.println("Press 2 to login as an advisor");
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
                    loginAsAStudent(department);
                    break;
                case 2://login as an advisor
                    loginAsAnAdvisor(department);
                    break;
                default://default case
                    break;
            }
        }//While loop end
    }//displayConsoleUI end

    private static void loginAsAStudent(Department department) {
        short var = -1;
        boolean flag = false;
        Student student = null;

        while (var != 0 && !flag) {
            System.out.println("1 to enter student number");
            System.out.println("0 to cancel");
            try{
                Scanner input = new Scanner(System.in);
                var = input.nextShort();
            }catch (Exception e){
                System.out.println(e);
            }

            switch (var) {
                case 0:
                    return;
                case 1:
                    String enteredNo = null;
                    try{
                        Scanner input2 = new Scanner(System.in);
                        enteredNo = input2.next();
                    }catch (Exception e){
                        System.out.println(e);
                        break;
                    }
                    student = department.getStudentByStudentNo(enteredNo);
                    if (student == null) {
                        System.out.println("Student cannot found!");
                        break;
                    }
                    flag = true;
                    break;
                default:
                    break;
            }
        }

        short choice = -1;
        while (choice != 0) {
            System.out.println("0 to exit");
            System.out.println("1 to list courses taken");
            System.out.println("2 to print transcript");
            System.out.println("3 to register to new course");
            System.out.println("4 to to see available courses");
            try{
                Scanner input2 = new Scanner(System.in);
                choice = input2.nextShort();
            }catch (Exception e){
                System.out.println(e);
            }
            switch (choice) {
                case 0:
                    break;
                case 1://get courses taken
                    for (String code : student.getCourseCodes()) {
                        System.out.println(department.getCourseByCourseCode(code));
                    }
                    break;
                case 2://display transcript
                    student.getTranscript().printTakenCoursesStatus();
                    break;
                case 3://new course registration
                    System.out.println("Enter the course code");
                    String courseCode = null;
                    try{
                        Scanner scanner = new Scanner(System.in);
                        courseCode = scanner.next();
                    }catch (Exception e){
                        System.out.println(e);
                        break;
                    }
                    student.registerToNewCourse(courseCode, new IDGenerator().generateNewID(IDType.RegistrationID));
                    break;
                case 4:
                    System.out.println("Available courses:");
                    printAvailableCourses(student);
                default://default case
                    break;
            }

        }//While loop end
    }//Login as a student end

    private static void loginAsAnAdvisor(Department department) {
        short var = -1;
        boolean flag = false;
        Advisor advisor = null;

        while (var != 0 && !flag) {
            System.out.println("1 to enter advisor staff number");
            System.out.println("0 to cancel");

            try{
                Scanner input = new Scanner(System.in);
                var = input.nextShort();
            }catch (Exception e){
                System.out.println(e);
            }

            switch (var) {
                case 0:
                    return;
                case 1:
                    String enteredNo = null;
                    try{
                        Scanner input2 = new Scanner(System.in);
                        enteredNo = input2.next();
                    }catch (Exception e){
                        System.out.println(e);
                        break;
                    }
                    advisor = department.getAdvisorByStaffNo(enteredNo);
                    if (advisor == null) {
                        System.out.println("Advisor cannot found!");
                        break;
                    }
                    flag = true;
                    break;
                default:
                    break;
            }
        }

        short choice = -1;
        while (choice != 0) {
            System.out.println("0 to exit");
            System.out.println("1 to list active registrations");
            System.out.println("2 to list students you advise");
            System.out.println("3 to proceed a registration");
            System.out.println("4 to list teaching courses");
            System.out.println("5 to grade ");
            System.out.println("");
            try{
                Scanner input2 = new Scanner(System.in);
                choice = input2.nextShort();
            }catch (Exception e){
                System.out.println(e);
            }

            switch (choice) {
                case 0:
                    break;
                case 1://get active registrations
                    System.out.println(advisor.getActiveRegistrations());
                    break;
                case 2://list students you advise
                    for (String code : advisor.getStudentNumbers()) {
                        System.out.println(department.getStudentByStudentNo(code));
                    }
                    break;
                case 3://proceed a registration
                    String registrationNo, registrationStatusStr;
                    try{
                        Scanner input = new Scanner(System.in);
                        System.out.println("Enter registration no.");
                        registrationNo = input.next();
                        System.out.println("Enter status(Active-Rejected-Confirmed)");
                        registrationStatusStr = input.next();
                    }catch (Exception e){
                        System.out.println(e);
                        break;
                    }
                    RegistrationStatus status = null;
                    if (registrationStatusStr.equalsIgnoreCase("Confirmed")) {
                        status = RegistrationStatus.Confirmed;
                    } else if (registrationStatusStr.equalsIgnoreCase("Active")) {
                        status = RegistrationStatus.Active;
                    } else if (registrationStatusStr.equalsIgnoreCase("Rejected")) {
                        status = RegistrationStatus.Rejected;
                    } else {
                        System.out.println("Invalid registration status!");
                        break;
                    }
                    advisor.proceedTheRegistration(registrationNo, status);
                    break;
                case 4:
                    listCoursesForLecturer(department, advisor);
                default://default case
                    break;
            }

        }//While loop end
    }//Login as an advisor end

/*
    private static void loginAsLecturer(Department department) {
        short var = -1;
        boolean flag = false;
        Lecturer lecturer = null;

        while (var != 0 && !flag) {
            System.out.println("1 to enter lecturer staff number");
            System.out.println("0 to cancel");
            Scanner input = new Scanner(System.in);
            var = input.nextShort();
            switch (var) {
                case 0:
                    return;
                case 1:
                    Scanner input2 = new Scanner(System.in);
                    String enteredNo = input2.next();
                    lecturer = department.getLecturerByStaffNo(enteredNo);
                    if (lecturer == null) {
                        System.out.println("Lecturer cannot found!");
                        break;
                    }
                    flag = true;
                    break;
                default:
                    break;
            }
        }

        short choice = -1;
        while (choice != 0) {
            System.out.println("0 to exit");
            System.out.println("1 to list teaching courses");
            System.out.println("2 to list students you advise");
            System.out.println("3 to proceed a registration");
            System.out.println("");
            Scanner input2 = new Scanner(System.in);
            choice = input2.nextShort();
            switch (choice) {
                case 0:
                    break;
                case 1://get active registrations
                    listCoursesForLecturer(department, lecturer);
                    break;
                case 2://list students you advise
                    //for(String code : advisor.getStudentNumbers()){
                    //    System.out.println(department.getStudentByStudentNo(code));
                    //}
                    break;
                case 3://proceed a registration
                    Scanner input = new Scanner(System.in);
                    String registrationNo, registrationStatusStr;
                    System.out.println("Enter registration no.");
                    registrationNo = input.next();
                    System.out.println("Enter status(Active-Rejected-Confirmed)");
                    registrationStatusStr = input.next();
                    RegistrationStatus status = null;
                    if (registrationStatusStr.equalsIgnoreCase("Confirmed")) {
                        status = RegistrationStatus.Confirmed;
                    } else if (registrationStatusStr.equalsIgnoreCase("Active")) {
                        status = RegistrationStatus.Active;
                    } else if (registrationStatusStr.equalsIgnoreCase("Rejected")) {
                        status = RegistrationStatus.Rejected;
                    } else {
                        System.out.println("Invalid registration status!");
                        break;
                    }
                    //advisor.proceedTheRegistration(registrationNo,status);
                    break;
                default://default case
                    break;
            }

        }//While loop end
    }*/


    private static void listCoursesForLecturer(Department department, Lecturer lecturer) {

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

    private static void showCourseOptions(Course course) {
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

    private static void gradeStudent(Student student, Course course) {

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

    private static void printAvailableCourses(Student student){

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

