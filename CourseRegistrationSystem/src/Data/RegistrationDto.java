package Data;

import Domain.Registration;
import Domain.RegistrationStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationDto {
    public String registrationNo;
    public String studentNo;
    public String advisorNo;
    public String courseCode;
    public RegistrationStatus status;

    public RegistrationDto(){}

    public RegistrationDto(Registration registration){
        this.registrationNo = registration.getRegistrationNo();
        this.studentNo = registration.getStudentNo();
        this.advisorNo = registration.getAdvisorNo();
        this.courseCode = registration.getCourseCode();
        this.status = registration.getStatus();
    }
}
