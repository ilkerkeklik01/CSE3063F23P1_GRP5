package Domain;
class CourseSection{
    private String courseCode;
    private String courseSectionNo;
    private String[] days;
    public CourseSection(String courseCode, String courseSectionNo, String[] days){
        this.courseCode = courseCode;
        this.courseSectionNo = courseSectionNo;
        this.days = days;
    }

    public String getcourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseSectionNo() {
        return courseCode;
    }

    public void setCourseSectionNo(String courseSectionNo) {
        this.courseSectionNo = courseSectionNo;
    }
}
