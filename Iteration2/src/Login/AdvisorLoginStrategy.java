package Login;

import Domain.Advisor;
import Domain.Department;
import Domain.RegistrationStatus;

import java.util.Scanner;

public class AdvisorLoginStrategy implements ILoginStrategy {
    @Override
    public void login(Department department) {
        loginAsAnAdvisor(department);
    }

    private void loginAsAnAdvisor(Department department) {
        short var = -1;
        boolean flag = false;
        Advisor advisor = null;
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
            advisor = department.getAdvisorByStaffNo(enteredNo);
            if (advisor == null) {
                continue;
            }
            flag = true;
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
                case 1:                                 //get active registrations
                    System.out.println(advisor.getActiveRegistrations());
                    break;
                case 2:                                 //list students you advise
                    for (String code : advisor.getStudentNumbers()) {
                        System.out.println(department.getStudentByStudentNo(code));
                    }
                    break;
                case 3:                                     //proceed a registration
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
                    loginHelper.listCoursesForLecturer(department, advisor);
                default://default case
                    break;
            }

        }//While loop end
    }//Login as an advisor end
}
