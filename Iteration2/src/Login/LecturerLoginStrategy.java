package Login;

import Domain.Department;
import Domain.Lecturer;
import Domain.RegistrationStatus;

import java.util.Scanner;

public class LecturerLoginStrategy implements ILoginStrategy{
    @Override
    public void login(Department department) {
        loginAsLecturer(department);
    }

    private static void loginAsLecturer(Department department) {
        short var = -1;
        boolean flag = false;
        Lecturer lecturer = null;

        LoginHelper loginHelper = new LoginHelper();

        while (var != 0 && !flag) {


            System.out.println("Enter your staff number");
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
            lecturer = department.getLecturerByStaffNo(enteredNo);
            if (lecturer == null) {
                continue;
            }
            flag = true;
        }

        short choice = -1;
        while (choice != 0) {
            System.out.println("0 to exit");
            System.out.println("1 to list teaching courses");
            System.out.println("");
            Scanner input2 = new Scanner(System.in);
            choice = input2.nextShort();
            switch (choice) {
                case 0:
                    break;
                case 1://get active registrations
                    loginHelper.listCoursesForLecturer(department, lecturer);
                    break;
                default://default case
                    break;
            }

        }//While loop end
    }

}
