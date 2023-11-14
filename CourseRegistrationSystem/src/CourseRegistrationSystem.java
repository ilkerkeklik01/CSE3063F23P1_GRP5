import Domain.*;

import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Department department = Department.getInstance();


        testMethod(department);
        //getEverything(department);


        System.out.println();

        displayConsoleUI();
    }

    public static void getEverything(Department department){
        System.out.println("\nGet all people\n");
        for(Person person : department.getAllPeople()){
            System.out.println(person.getClass().getName()+" "+person.getFName()+" "+person.getLName());
        }
        System.out.println("\nGet all Students\n");
        for(Student student : department.getAllStudents()){
            System.out.println(student.getClass().getName()+" "+student.getFName()+" "+student.getLName());
        }
        System.out.println("\nGet all Advisors\n");
        for(Advisor advisor : department.getAllAdvisors()){
            System.out.println(advisor.getClass().getName()+" "+advisor.getFName()+" "+advisor.getLName());
        }
        System.out.println("\nGet all Courses\n");
        for(Course course : department.getAllCourses()){
            System.out.println(course.getClass().getName()+" "+course.getCourseCode()+" "+course.getCourseName());
        }
        System.out.println("\nGet all Registrations\n");
        for(Registration registration : department.getAllRegistrations()){
            System.out.println(registration);
        }
    }
    public static void testMethod(Department department1){

            IDGenerator idGenerator = new IDGenerator();
            Advisor advisor = new Advisor("Ilker","Keklik", null, idGenerator.generateNewID(IDType.StaffID),new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>());
            department1.addAnObject(advisor);
            Advisor advisor2 = new Advisor("Burkay","Keklik", null,idGenerator.generateNewID(IDType.StaffID),new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>());
            department1.addAnObject(advisor2);

            Student student = new Student("Kerim","Hasan",null,idGenerator.generateNewID(IDType.StudentID),new ArrayList<String>(),advisor.getStaffNo(),new ArrayList<String>());
            department1.addAnObject(student);
            Student student2 = new Student("Yusuf","Duman",null,idGenerator.generateNewID(IDType.StudentID),new ArrayList<String>(),advisor.getStaffNo(),new ArrayList<String>());
            department1.addAnObject(student2);

            Course course = new Course("Database","CSE3020",new ArrayList<String>(),"",new ArrayList<String>(),new ArrayList<String>());
            department1.addAnObject(course);
            Course course2 = new Course("OOPP","CSE3070",new ArrayList<String>(),"",new ArrayList<String>(),new ArrayList<String>());
            department1.addAnObject(course2);

            student.registerToNewCourse("CSE3070",idGenerator.generateNewID(IDType.RegistrationID));
            //System.out.println("Before proceed:"+student.getCourseCodes());
            //advisor.proceedTheRegistration("1",RegistrationStatus.Confirmed);
            //System.out.println("After proceed:"+student.getCourseCodes());
            //System.out.println("After2 proceed:"+student2.getCourseCodes());
            System.out.println(advisor.getActiveRegistrations());
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
