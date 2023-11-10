package Domain;
class CourseSection{

    private String courseCode;
    private int courseSchedule;
    private int examCount;
    private int credit;

    public CourseSection(String courseCode, int courseSchedule, int credit, int examCount){
        this.courseCode = courseCode;
        this.credit = credit;
        this.courseSchedule = courseSchedule;
        this.examCount = examCount;
    }

    public String getcourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getCourseSchedule() {
        return courseSchedule;
    }

    public void setCourseSchedule(int courseSchedule) {
        this.courseSchedule = courseSchedule;
    }

    public int getExamCount() {
        return examCount;
    }

    public void setExamCount(int examCount) {
        this.examCount = examCount;
    }
}
