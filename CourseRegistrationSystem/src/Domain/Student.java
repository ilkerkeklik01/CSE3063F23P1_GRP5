package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Student extends Person{

    private String studentNo;
    private Collection<String> courseCodes;
    private String advisorNo;
    private Collection<String> registrationNumbers;


    public Student(String FName, String LName, Date birthdate, String studentNo, Collection<String> courseCodes, String advisorNo,Collection<String> registrationNumbers) {
        super(FName, LName, birthdate);
        setStudentNo(studentNo);
        setCourseCodes(courseCodes);
        setAdvisorNo(advisorNo);
        setRegistrationNumbers(registrationNumbers);
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

}
