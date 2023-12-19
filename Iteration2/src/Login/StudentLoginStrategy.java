package Login;

import Domain.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class StudentLoginStrategy implements ILoginStrategy{
    @Override
    public void login(Department department) {
        loginAsAStudent(department);
    }


    private void loginAsAStudent(Department department) {

        IDGenerator regisIdGenerator = new RegistrationIDGenerator();

        short var = -1;
        boolean flag = false;
        Student student = null;
        LoginHelper loginHelper = new LoginHelper();

        while (!flag) {
            System.out.println("Enter your student number");
            System.out.println("0 to cancel");

            String enteredNo;


            try{
                Scanner input2 = new Scanner(System.in);
                enteredNo = input2.next();
            }catch (Exception e){
                System.out.println(e);
                break;
            }
            if(enteredNo.equals("0")){
                return;
            }
            student = department.getStudentByStudentNo(enteredNo);
            if (student == null) {
                continue;
            }
            flag = true;


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
                        student.checkCourseEligibility(courseCode); // THROWS ERROR IF NOT ELIGIBLE
                        String selectedCourseSectionNo = displaycourseSelection(courseCode);
                        student.registerToNewCourse(courseCode, regisIdGenerator.generateID(), selectedCourseSectionNo);
                        break;
                    }catch (Exception e){
                        System.out.println(e);
                        break;
                    }
                    
                case 4:
                    System.out.println("Available courses:");
                    loginHelper.printAvailableCourses(student);
                default://default case
                    break;
            }

        }//While loop end
    }//Login as a student end


    private String displaycourseSelection(String courseCode){        
        Course course = Department.getInstance().getCourseByCourseCode(courseCode);
        course.printCourseSections();

        System.out.println("Enter the section number:");
        Scanner input = new Scanner(System.in);
        String chosenSectionNo = input.next();
        
        return chosenSectionNo;
    }


}
