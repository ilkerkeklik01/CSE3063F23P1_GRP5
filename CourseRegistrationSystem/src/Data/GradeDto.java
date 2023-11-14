package Data;

import Domain.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class GradeDto {
    public String courseCode;
    public float numGrade;
    public String letterGrade;
    public boolean isPassed;

    public GradeDto (Grade grade){
        this.courseCode = grade.getCourse().getCourseCode();
        this.numGrade = grade.getNumericGrade();
        this.letterGrade = grade.getLetterGrade();
        this.isPassed = grade.isPassed();
    }
    public GradeDto(){};
}
