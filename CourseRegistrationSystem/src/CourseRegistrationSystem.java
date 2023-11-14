import Domain.*;

import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Department department = Department.getInstance();


        testMethod(department);
        //getEverything(department);


        System.out.println();

        displayConsoleUI(department);
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

            student.registerToNewCourse("CSE3070","1");
            //System.out.println("Before proceed:"+student.getCourseCodes());
            advisor.proceedTheRegistration("1",RegistrationStatus.Confirmed);
            //System.out.println("After proceed:"+student.getCourseCodes());
            //System.out.println("After2 proceed:"+student2.getCourseCodes());
            System.out.println(advisor.getActiveRegistrations());
    }

    private static void displayConsoleUI(Department department){
        short choice = -1;

        while(choice!=0){
            System.out.println("Press 1 to add a Student");
            System.out.println("Press 2 to add an Advisor");
            System.out.println("Press 3 to add a Lecturer");
            System.out.println("Press 4 to add a Course");
            System.out.println("Press 5 to login as a student");
            System.out.println("Press 6 to login as an advisor");
            System.out.println("Press 0 to exit!");
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
                    loginAsAStudent(department);
                    break;
                case 6://login as an advisor
                    loginAsAnAdvisor(department);
                    break;
                default://default case
                    break;
            }
        }//While loop end
    }//displayConsoleUI end

    private static void loginAsAStudent(Department department){
        short var = -1;
        boolean flag = false;
        Student student=null;

        while(var!=0&&!flag){
            System.out.println("1 to enter student number");
            System.out.println("0 to cancel");
            Scanner input = new Scanner(System.in);
            var = input.nextShort();
            switch (var){
                case 0: return;
                case 1:
                    Scanner input2 = new Scanner(System.in);
                    String enteredNo = input2.next();
                    student = department.getStudentByStudentNo(enteredNo);
                    if(student == null) {
                        System.out.println("Student cannot found!");
                        break;
                    }
                    flag = true;
                    break;
                default: break;
            }
        }

        short choice = -1;
        while(choice!=0){
            System.out.println("0 to exit");
            System.out.println("1 to list courses taken");
            System.out.println("4 to register to new course");
            Scanner input2 = new Scanner(System.in);
            choice = input2.nextShort();
            switch (choice){
                case 0:
                    break;
                case 1://get courses taken
                    for(String code : student.getCourseCodes()){
                        System.out.println(department.getCourseByCourseCode(code));
                    }
                    break;
                case 2://display transcript
                    break;
                case 3://
                    break;
                case 4://new course registration
                    System.out.println("Enter the course code");
                    Scanner scanner = new Scanner(System.in);
                    String courseCode = scanner.next();
                    //Warning generate course registration id is missing
                    student.registerToNewCourse(courseCode,new IDGenerator().generateNewID(IDType.StaffID));
                    break;
                case 5://
                    break;
                default://default case
                    break;
            }

        }//While loop end
    }//Login as a student end

    private static void loginAsAnAdvisor(Department department){
        short var = -1;
        boolean flag = false;
        Advisor advisor=null;

        while(var!=0&&!flag){
            System.out.println("1 to enter advisor staff number");
            System.out.println("0 to cancel");
            Scanner input = new Scanner(System.in);
            var = input.nextShort();
            switch (var){
                case 0: return;
                case 1:
                    Scanner input2 = new Scanner(System.in);
                    String enteredNo = input2.next();
                    advisor = department.getAdvisorByStaffNo(enteredNo);
                    if(advisor == null) {
                        System.out.println("Advisor cannot found!");
                        break;
                    }
                    flag = true;
                    break;
                default: break;
            }
        }

        short choice = -1;
        while(choice!=0){
            System.out.println("0 to exit");
            System.out.println("1 to list active registrations");
            System.out.println("2 to list students you advise");
            System.out.println("3 to proceed a registration");
            System.out.println("");
            Scanner input2 = new Scanner(System.in);
            choice = input2.nextShort();
            switch (choice){
                case 0:
                    break;
                case 1://get active registrations
                    System.out.println(advisor.getActiveRegistrations());
                    break;
                case 2://list students you advise
                    for(String code : advisor.getStudentNumbers()){
                        System.out.println(department.getStudentByStudentNo(code));
                    }
                    break;
                case 3://proceed a registration
                    Scanner input = new Scanner(System.in);
                    String registrationNo,registrationStatusStr;
                    System.out.println("Enter registration no.");
                    registrationNo = input.next();
                    System.out.println("Enter status(Active-Rejected-Confirmed)");
                    registrationStatusStr = input.next();
                    RegistrationStatus status = null;
                    if(registrationStatusStr.equalsIgnoreCase("Confirmed")){
                        status = RegistrationStatus.Confirmed;
                    }else if(registrationStatusStr.equalsIgnoreCase("Active")){
                        status=RegistrationStatus.Active;
                    } else if (registrationStatusStr.equalsIgnoreCase("Rejected")) {
                        status = RegistrationStatus.Rejected;
                    }else{
                        System.out.println("Invalid registration status!");
                        break;
                    }
                    advisor.proceedTheRegistration(registrationNo,status);
                    break;
                default://default case
                    break;
            }

        }//While loop end
    }//Login as an advisor end


}
