package Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Grade {
    private  Course course;
    private  float numGrade;
    private String letterGrade;
    private boolean isPassed;
    private float numericGradeForGano;

    public Grade(Course course, float numericGrade) {
        this.course = course;
        this.numGrade = numericGrade;
        letterGradeCalculation();
    }
    public Grade(){

    }
    private void letterGradeCalculation() {
        if (numGrade >= 88) {
            isPassed = true;
            letterGrade = "AA";
            numericGradeForGano = 4;
        } else if (numGrade >= 81) {
            isPassed = true;
            letterGrade = "BA";
            numericGradeForGano = 3.5f;
        } else if (numGrade >= 74) {
            isPassed = true;
            letterGrade = "BB";
            numericGradeForGano = 3;
        } else if (numGrade >= 67) {
            isPassed = true;
            letterGrade = "CB";
            numericGradeForGano = 2.5f;
        } else if (numGrade >= 60) {
            isPassed = true;
            letterGrade = "CC";
            numericGradeForGano = 2;
        } else if (numGrade >= 53) {
            isPassed = true;
            letterGrade = "DC";
            numericGradeForGano = 1.5f;
        } else if (numGrade >= 46) {
            isPassed = true;
            letterGrade = "DD";
            numericGradeForGano = 1;
        }else {
            isPassed = false;
            letterGrade = "FF";
            numericGradeForGano = 0;
        }
    }

    @JsonIgnore
    public Course getCourse() {
        return course;
    }

    public float getNumericGrade() {
        return numGrade;
    }

    public String getLetterGrade() {
        return letterGrade;
    }


    public boolean isPassed() {
        return isPassed;
    }


    public void setPassed(boolean passed) {
        isPassed = passed;
    }
    public void setNumericGrade(float numericGrade){
        this.numGrade = numericGrade;
        letterGradeCalculation();
    }

    public float getNumericGradeForGano() {
        return numericGradeForGano;
    }


}
