package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Advisor extends Lecturer {



    private Collection<String> studentNumbers;
    private Collection<String> registrationNumbers;


    public Advisor(String FName, String LName, Date birthdate, String staffNo, Collection<String> courseIds, Collection<String> studentNumbers, Collection<String> registrationNumbers) {
        super(FName, LName, birthdate, staffNo, courseIds);
        setStudentNumbers(studentNumbers);
        setRegistrationNumbers(registrationNumbers);
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
