package Data;

import Domain.Course;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseDto {
    public String courseName;
    public String courseCode;
    public Collection<String> lecturersNumbers;
    public String courseSectionNo;
    public Collection<String> studentNumbers;
    public Collection<String> prerequisitesIds;
    public int credit;

    public CourseDto(){}

    public CourseDto(Course course){
        this.courseName = course.getCourseName();
        this.courseCode = course.getCourseCode();
        this.courseSectionNo =course.getCourseSectionNo();
        this.lecturersNumbers = course.getLecturersNumbers();
        this.studentNumbers = course.getLecturersNumbers();
        this.prerequisitesIds = course.getPrerequisitesIds();
        this.credit = course.getCredit();
    }
}
