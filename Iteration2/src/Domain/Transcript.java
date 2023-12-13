package Domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transcript {
    private List<Grade> grades;
    private int completedCredits;
    private float gano;

    public Transcript() {
        this.grades = new ArrayList<>();
    }


    private void updateGano() {
        float totalNumericalGrade = 0;
        for (Grade grade : grades) {
            totalNumericalGrade += grade.getNumericGradeForGano();
        }
        float updatedGano = (totalNumericalGrade / completedCredits);
        setGano(updatedGano);
    }



    public void addGrade(String courseCode, float numericGrade) {
        Course course = Department.getInstance().getCourseByCourseCode(courseCode);

        for (Grade grade : grades){
            if(grade.getCourse().getCourseCode() == courseCode){
                grade.setNumericGrade(numericGrade);
                return;
            }
        }
        Grade newGrade = new Grade(course, numericGrade);
        grades.add(newGrade);
        updateTotalCreditsandGano();
    }


    public void updateTotalCreditsandGano(){
        int totalCredits = 0;
        for(Grade grade: grades){
            totalCredits += grade.getCourse().getCredit();
        }
        setCompletedCredits(totalCredits);
        updateGano();
    }



//#region print methods
    public void printTakenCoursesStatus() {
        System.out.println("Completed Credits: " + getCompletedCredits() + "\n");
        System.out.println("Completed Courses" + "\n");
        printPassedCourses();
        printFailedCourses();
    }
    public void printPassedCourses() {
        for (Grade grade : grades) {
            if (grade.isPassed()) {
                System.out.println(grade.getCourse().getCourseName() + " " + grade.getCourse().getCourseCode() + " " 
                + grade.getCourse().getCredit() + " credits" + " " + grade.getLetterGrade());
            }
        }
    }
    public ArrayList<String> getPassedCourseCodes() {
        ArrayList<String> arr = new ArrayList<>();
        for (Grade grade : grades) {
            if (grade.isPassed()) {
                arr.add(grade.getCourse().getCourseCode());
            }
        }
        return arr;
    }

    public void printFailedCourses() {
        for (Grade grade : grades) {
            if (!grade.isPassed()) {
                System.out.println(grade.getCourse().getCourseName() + " " + grade.getCourse().getCourseCode() + " " 
                + grade.getCourse().getCredit() + " credits" + " " + grade.getLetterGrade());
            }
        }
    }


//#endregion

    public float getGano() {
        updateTotalCreditsandGano();
        return gano;
    }

    private void setGano(float gano) {
        this.gano = gano;
    }

    @JsonIgnore
    public List<Grade> getGrades() {
        return grades;
    }

    private void setCompletedCredits(int completedCredits) {
        this.completedCredits = completedCredits;
    }

    public int getCompletedCredits() {
        return completedCredits;
    }

    public ArrayList<Course> getCompletedCourses() {
        ArrayList<Course> completedCourses = new ArrayList<>();
        for(Grade grade : grades){
            completedCourses.add(grade.getCourse());
        }
        return completedCourses;
    }
    
    @JsonIgnore
    public ArrayList<String> getSuccessfullyCompletedCourseCodes() {
        ArrayList<String> completedCourseCodes = new ArrayList<>();
        for(Grade grade : grades){
            if(grade.isPassed())
                completedCourseCodes.add(grade.getCourse().getCourseCode());
        }
        return completedCourseCodes;
    }

    @JsonIgnore
    public ArrayList<String> getFailedCourseCodes() {
        ArrayList<String> failedCourseCodes = new ArrayList<>();
        for(Grade grade : grades){
            if(!grade.isPassed())
                failedCourseCodes.add(grade.getCourse().getCourseCode());
        }
        return failedCourseCodes;
    }

    @JsonIgnore
    public ArrayList<String> getAllCompletedCourseCodes(){
        ArrayList<String> allCompletedCourseCodes = new ArrayList<>();
        for(Grade grade : grades){
            allCompletedCourseCodes.add(grade.getCourse().getCourseCode());
        }
        return allCompletedCourseCodes;
    }

    
}