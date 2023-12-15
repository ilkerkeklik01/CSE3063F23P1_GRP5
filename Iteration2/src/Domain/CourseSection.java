package Domain;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseSection{

    private String courseSectionNo;
    private int noOfStudents;
    private int Quota;
    private ArrayList<String> sectionTimes;
    private String courseCode;


    public CourseSection(String courseCode, String courseSectionNo, int Quota, ArrayList<String> sectionTimes){
        this.noOfStudents = 0;
        this.courseCode = courseCode;
        this.courseSectionNo = courseSectionNo;
        this.Quota = Quota;
        this.sectionTimes = sectionTimes;
    }
    public CourseSection(){};


    public void addStudent(){
        noOfStudents++;
    }

    @JsonIgnore
    public boolean isFull(){
        return noOfStudents == Quota;
    }

    public String getcourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseSectionNo() {
        return courseSectionNo;
    }

    public void setCourseSectionNo(String courseSectionNo) {
        this.courseSectionNo = courseSectionNo;
    }

    public int getNoOfStudents() {
        return noOfStudents;
    }
    
    public void setNoOfStudents(int noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public int getQuota() {
        return Quota;
    }

    public void setQuota(int Quota) {
        this.Quota = Quota;
    }

    public ArrayList<String> getSectionTimes() {
        return sectionTimes;
    }

    public void setSectionTimes(ArrayList<String> sectionTime) {
        this.sectionTimes = sectionTime;
    }


}
