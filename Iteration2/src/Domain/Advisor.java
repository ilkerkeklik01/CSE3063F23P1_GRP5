package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 @JsonIgnoreProperties(ignoreUnknown = true)
public class Advisor extends Lecturer  {



    private Collection<String> studentNumbers;
    private Collection<String> registrationNumbers;


    public Advisor(String FName, String LName, Date birthdate, String staffNo, Collection<String> courseIds, Collection<String> studentNumbers, Collection<String> registrationNumbers) {
        super(FName, LName, birthdate, staffNo, courseIds);
        setStudentNumbers(studentNumbers);
        setRegistrationNumbers(registrationNumbers);
    }

    public Advisor(){
        super();
    };


    public Collection<Registration> getActiveRegistrations(){
        ArrayList<Registration> registrations = new ArrayList<>();
        ArrayList<Registration> allRegistrations = (ArrayList<Registration>) Department.getInstance().getAllRegistrations();

        for(Registration each : allRegistrations){
            if(each.getStatus()==RegistrationStatus.Active&&each.getAdvisorNo().equals(this.getStaffNo())){
                registrations.add(each);
            }
        }
        return registrations;
    }
    private Registration isActiveRegistration(String registrationNo){
        ArrayList<Registration> activeRegistrations = (ArrayList<Registration>) getActiveRegistrations();

        for(Registration each : activeRegistrations){
            if(each.getRegistrationNo().equals(registrationNo)){
                return each;
            }
        }
        return null;
    }
    public void proceedTheRegistration(String registrationNo,RegistrationStatus status){
        Registration registration = isActiveRegistration(registrationNo);
        if(registration==null){
            System.out.println(registrationNo+ " is not an active registration or is not a valid registration");
            return;
        }
        registration.setStatus(status);
        if(status == RegistrationStatus.Confirmed){
            Student student = Department.getInstance().getStudentByStudentNo(registration.getStudentNo());
            student.getCourseCodes().add(registration.getCourseCode());
            Course course = Department.getInstance().getCourseByCourseCode(registration.getCourseCode());
            course.getStudentNumbers().add(student.getStudentNo());
        }else{
            System.out.println("Registration is not confirmed");
        }
    }


    public Collection<String> getStudentNumbers() {
        return studentNumbers;
    }

    public void setStudentNumbers(Collection<String> studentNumbers) {
        this.studentNumbers = studentNumbers;
    }

    public Collection<String> getRegistrationNumbers() {
        return registrationNumbers;
    }

    public void setRegistrationNumbers(Collection<String> registrationNumbers) {
        this.registrationNumbers = registrationNumbers;
    }





}
