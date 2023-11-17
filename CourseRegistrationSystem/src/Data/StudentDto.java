package Data;

import Domain.Person;
import Domain.Student;
import Domain.Transcript;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDto extends PersonDto {
    public String studentNo;
    public Collection<String> courseCodes;
    public String advisorNo;
    public Collection<String> registrationNumbers;
    public Transcript transcript;

    public StudentDto (Student student){
        super(student);
        this.advisorNo = student.getAdvisorNo();
        this.studentNo = student.getStudentNo();
        this.courseCodes = student.getCourseCodes();
        this.registrationNumbers = student.getRegistrationNumbers();
        this.transcript = student.getTranscript();
    }

    public StudentDto(){};
}
