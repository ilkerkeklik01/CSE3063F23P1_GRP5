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
    private ArrayList<CourseSection> courseSections;
    private Collection<String> studentNumbers;
    private Collection<String> prerequisitesIds;
    private int credit;
    private int semester;

    public Course(String courseName, String courseCode, Collection<String> lecturersNumbers, ArrayList<CourseSection> courseSection, Collection<String> studentNumbers, Collection<String> prerequisitesIds, int credit) {
        this.credit = credit;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.lecturersNumbers = lecturersNumbers;
        this.courseSections = courseSection;
        this.studentNumbers = studentNumbers;
        this.prerequisitesIds = prerequisitesIds;
    }

    public Course(){};

    public void AddSection(CourseSection section){
        if(courseSections == null)
            courseSections = new ArrayList<CourseSection>();
        courseSections.add(section);
    }



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

    public ArrayList<CourseSection> getCourseSections() {
        return courseSections;
    }

    public void setCourseSections(ArrayList<CourseSection> courseSectionNo) {
        this.courseSections = courseSectionNo;
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

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", lecturersNumbers=" + lecturersNumbers +
                ", courseSectionNo='" + courseSections.get(0) + courseSections.get(1)+ '\'' +
                ", studentNumbers=" + studentNumbers +
                ", prerequisitesIds=" + prerequisitesIds +
                ", credit=" + credit +
                ", semester=" + semester +
                '}';
    }
}
