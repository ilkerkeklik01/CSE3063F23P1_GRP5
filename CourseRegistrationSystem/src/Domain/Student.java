package Domain;

import java.util.Collection;
import java.util.Date;

public class Student extends Person {

    private String studentNo;
    private Collection<String> courseCodes;
    private String advisorNo;
    private Collection<String> registrationNumbers;
    private Transcript transcript;


    public Student(String FName, String LName, Date birthdate, String studentNo, Collection<String> courseCodes, String advisorNo,Collection<String> registrationNumbers) {
        super(FName, LName, birthdate);
        setStudentNo(studentNo);
        setCourseCodes(courseCodes);
        setAdvisorNo(advisorNo);
        setRegistrationNumbers(registrationNumbers);
        Department.getInstance().getAdvisorByStaffNo(advisorNo).getStudentNumbers().add(studentNo);
        this.transcript = new Transcript();
    }

    public void registerToNewCourse(String courseCode,String newRegistrationNo){
        Course course = Department.getInstance().getCourseByCourseCode(courseCode);
        if(course==null){
            System.out.println("There is no such a course: "+courseCode);
            return;
        }

          if(!this.transcript.getPassedCourseCodes().containsAll(course.getPrerequisitesIds())){
            System.out.println("You have to pass the prerequisites of the course: "+courseCode);
            return;
          }
        


        
        Registration registration = new Registration(newRegistrationNo,this.getStudentNo(),this.getAdvisorNo(),courseCode,RegistrationStatus.Active);
        this.getRegistrationNumbers().add(registration.getRegistrationNo());
        Advisor advisor = Department.getInstance().getAdvisorByStaffNo(getAdvisorNo());
        advisor.getRegistrationNumbers().add(registration.getRegistrationNo());
        Department.getInstance().getAllRegistrations().add(registration);
    }

    public Transcript getTranscript() {
        return transcript;
    }
    

    public Collection<String> getRegistrationNumbers() {
        return registrationNumbers;
    }

    public void setRegistrationNumbers(Collection<String> registrationNumbers) {
        this.registrationNumbers = registrationNumbers;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public Collection<String> getCourseCodes() {
        return courseCodes;
    }

    public void setCourseCodes(Collection<String> courseCodes) {
        this.courseCodes = courseCodes;
    }

    public String getAdvisorNo() {
        return advisorNo;
    }

    public void setAdvisorNo(String advisorNo) {
        this.advisorNo = advisorNo;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentNo='" + studentNo + '\'' +
                ", courseCodes=" + courseCodes +
                ", advisorNo='" + advisorNo + '\'' +
                ", registrationNumbers=" + registrationNumbers +
                "} " + super.toString();
    }
}
