package Domain;


import java.util.ArrayList;
import java.util.List;

public class Transcript {
    private final List<Grade> grades;

    public int getCompletedCredits() {
        return completedCredits;
    }

    private void setCompletedCredits(int completedCredits) {
        this.completedCredits = completedCredits;
    }

    private int completedCredits;
    public Transcript() {
        this.grades = new ArrayList<>();
    }


    public void addGrade(Course course, int numericGrade) {
        Grade newGrade = new Grade(course, numericGrade);
        grades.add(newGrade);
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void printPassedCourses() {
        System.out.println("Passed Courses:");
        for (Grade grade : grades) {
            if (grade.isPassed()) {
                System.out.println(grade.getCourse().getCourseName() + "is passed");
            }
        }


    }

    public void calculateCompletedCredits(){
        int totalCredits = 0;
        for(Grade grade: grades){
            totalCredits += grade.getCourse().getCredit();
        }
        setCompletedCredits(totalCredits);
    }


}



