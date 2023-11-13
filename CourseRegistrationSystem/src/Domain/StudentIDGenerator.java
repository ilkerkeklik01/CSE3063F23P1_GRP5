package Domain;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class StudentIDGenerator extends IDGenerator {
    
    public StudentIDGenerator(){
        super();
        setDigitCount(8);
        setIdPrefix("120");
        setIdSuffix("");
        this.setUsedIDs(getUsedIDsByOtherStudents());
    }

    private List<String> getUsedIDsByOtherStudents(){

      Collection<Student> allStudents = Department.getInstance().getAllStudents();
        List<String> alreadyUsedIDs = new ArrayList<>();

        for (Student student : allStudents) {
            alreadyUsedIDs.add(student.getStudentNo());
        }
        return alreadyUsedIDs;
    } 

}
