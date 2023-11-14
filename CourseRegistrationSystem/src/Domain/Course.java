package Domain;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Course implements Searchable{

    private String courseName;
    private String courseCode;
    private Collection<String> lecturersNumbers;
    private String courseSectionNo;
    private Collection<String> studentNumbers;
    private Collection<String> prerequisitesIds;
    private int credit;


    public Course(String courseName, String courseCode, Collection<String> lecturersNumbers, String courseSectionNo, Collection<String> studentNumbers, Collection<String> prerequisitesIds, int credit) {
        this.credit = credit;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.lecturersNumbers = lecturersNumbers;
        this.courseSectionNo = courseSectionNo;
        this.studentNumbers = studentNumbers;
        this.prerequisitesIds = prerequisitesIds;
    }

    public Course(){};



    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Collection<String> getLecturersNumbers() {
        return lecturersNumbers;
    }

    public void setLecturersNumbers(Collection<String> lecturersIds) {
        this.lecturersNumbers = lecturersIds;
    }

    public String getCourseSectionNo() {
        return courseSectionNo;
    }

    public void setCourseSectionNo(String courseSectionNo) {
        this.courseSectionNo = courseSectionNo;
    }

    public Collection<String> getStudentNumbers() {
        return studentNumbers;
    }

    @JsonIgnore
    public Collection<Student> getStudents(){
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Student> allStudents = (ArrayList<Student>) Department.getInstance().getAllStudents();

        for(Student student : allStudents){
            if(this.getStudentNumbers().contains(student.getStudentNo())){
                students.add(student);
            }
        }
        return students;
    }

    public void setStudentNumbers(Collection<String> studentNumbers) {
        this.studentNumbers = studentNumbers;
    }

    public Collection<String> getPrerequisitesIds() {
        return prerequisitesIds;
    }

    public void setPrerequisitesIds(Collection<String> prerequisitesIds) {
        this.prerequisitesIds = prerequisitesIds;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                '}';
    }
}
