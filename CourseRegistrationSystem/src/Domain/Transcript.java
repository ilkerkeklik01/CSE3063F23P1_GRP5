package Domain;


import java.util.ArrayList;
import java.util.List;

public class Transcript {
    private final List<Grade> grades;
    private int completedCredits;

    public Transcript() {
        this.grades = new ArrayList<>();
    }

    public int getCompletedCredits() {
        return completedCredits;
    }

    private void setCompletedCredits(int completedCredits) {
        this.completedCredits = completedCredits;
    }




    public void addGrade(Course course, int numericGrade) {
        Grade newGrade = new Grade(course, numericGrade);
        grades.add(newGrade);
        updateTotalCredits();
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void printTakenCoursesStatus() {
        System.out.println("Completed Credits: " + getCompletedCredits() + "\n");
        System.out.println("Taken Courses :");
        printPassedCourses();
        System.out.println();
        printFailedCourses();
    }
    public void printPassedCourses() {
        System.out.println("Passed Courses:");
        for (Grade grade : grades) {
            if (grade.isPassed()) {
                System.out.println(grade.getCourse().getCourseName() + " " + grade.getCourse().getCourseCode() + grade.getCourse().getCredit());
            }
        }
    }
    public void printFailedCourses() {
        System.out.println("Failed Courses:");
        for (Grade grade : grades) {
            if (!grade.isPassed()) {
                System.out.println(grade.getCourse().getCourseName() + " " + grade.getCourse().getCourseCode() + grade.getCourse().getCredit());
            }
        }
    }




    public void updateTotalCredits(){
        int totalCredits = 0;
        for(Grade grade: grades){
            totalCredits += grade.getCourse().getCredit();
        }
        setCompletedCredits(totalCredits);
    }


}



