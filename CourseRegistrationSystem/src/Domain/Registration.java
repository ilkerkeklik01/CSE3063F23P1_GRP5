package Domain;

import java.util.ArrayList;
public class Registration {

    private String registrationNo;
    private String studentNo;
    private String advisorNo;
    private String courseCode;
    private RegistrationStatus status;

    public Registration(String registrationNo, String studentNo, String advisorNo, String courseCode, RegistrationStatus status) {
        this.registrationNo = registrationNo;
        this.studentNo = studentNo;
        this.advisorNo = advisorNo;
        this.courseCode = courseCode;
        this.status = status;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getAdvisorNo() {
        return advisorNo;
    }

    public void setAdvisorNo(String advisorNo) {
        this.advisorNo = advisorNo;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "registrationNo='" + registrationNo + '\'' +
                ", studentNo='" + studentNo + '\'' +
                ", advisorNo='" + advisorNo + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", status=" + status +
                '}';
    }
}
