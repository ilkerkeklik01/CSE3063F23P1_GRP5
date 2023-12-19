package Domain;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseSection{

    private String courseSectionNo;
    private int noOfStudents;
    private int Quota;
    private ArrayList<String> sectionTime;
    private String courseCode;


    public CourseSection(String courseCode, String courseSectionNo, int Quota, ArrayList<String> sectionTimes){
        this.noOfStudents = 0;
        this.courseCode = courseCode;
        this.courseSectionNo = courseSectionNo;
        this.Quota = Quota;
        this.sectionTime = sectionTimes;
    }
    public CourseSection(){};


    public void addStudent(){
        noOfStudents++;
    }

    public void printSections(){
        System.out.println("Course Section No: " + getCourseSectionNo() + " Quota: " + getQuota() + " No of Students: " + getNoOfStudents() + " Lecture Times:");
        for(String each : sectionTime){
            System.out.println(getDay(each)+ " " + each.substring(1,3) + ":00");
        }
        System.out.println();
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

    public ArrayList<String> getSectionTime() {
        return sectionTime;
    }

    public void setSectionTime(ArrayList<String> sectionTimes) {
        this.sectionTime = sectionTimes;
    }

    private String getDay(String time){
        String dayNo = time.substring(0,1);
        switch(dayNo){
            case "1":
                return "Monday";
            case "2":
                return "Tuesday";

            case "3":
                return "Wednesday";

            case "4":
                return "Thursday";
            
            case "5":
                return "Friday";
            
            default:
                return "Free Day";
        }
    }

}
