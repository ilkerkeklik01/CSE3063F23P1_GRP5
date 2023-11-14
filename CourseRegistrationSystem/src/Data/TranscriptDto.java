package Data;

import Domain.*;


// A data transfer object for creating transcript json files
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TranscriptDto {
    public String studentNo;
    public int completedCredits;
    public ArrayList<GradeDto> grades = new ArrayList<>();


    public TranscriptDto(Student student){
        studentNo = student.getStudentNo();
        Transcript transcript = student.getTranscript();
        completedCredits = transcript.getCompletedCredits();
        for (Grade grade : transcript.getGrades()){
            GradeDto gradeDto = new GradeDto(grade);
            grades.add(gradeDto);
        }
    }

    public TranscriptDto(){};

}
