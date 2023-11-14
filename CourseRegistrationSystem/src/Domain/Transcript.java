package Domain;


import java.util.ArrayList;
import java.util.List;

public class Transcript {
    private List<Grade> grades;
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
        updateTotalCredits();
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void printTakenCoursesStatus() {
        System.out.println("Completed Credits: " + getCompletedCredits() + "\n");
        System.out.println("Taken Courses" + "\n");
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




    public void updateTotalCredits(){
        int totalCredits = 0;
        for(Grade grade: grades){
            totalCredits += grade.getCourse().getCredit();
        }
        setCompletedCredits(totalCredits);
    }


}



