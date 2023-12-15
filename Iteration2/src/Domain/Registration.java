package Domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Registration implements Searchable{

    private String registrationNo;
    private String studentNo;
    private String advisorNo;
    private String courseCode;
    private RegistrationStatus status;
    private CourseSection courseSection;

    public Registration(String registrationNo, String studentNo, String advisorNo, String courseCode, RegistrationStatus status, CourseSection courseSection) {
        this.registrationNo = registrationNo;
        this.studentNo = studentNo;
        this.advisorNo = advisorNo;
        this.courseCode = courseCode;
        this.status = status;
        this.courseSection = courseSection;
    }

    public Registration(){}



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

    public CourseSection getCourseSection() {
        return courseSection;
    }

    public void setCourseSection(CourseSection courseSection) {
        this.courseSection = courseSection;
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
