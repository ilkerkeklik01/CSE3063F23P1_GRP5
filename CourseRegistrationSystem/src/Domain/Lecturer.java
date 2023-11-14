package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lecturer extends Staff {

    private Collection<String> courseIds;

    public Lecturer(String FName, String LName, Date birthdate, String staffNo, Collection<String> courseIds) {
        super(FName, LName, birthdate, staffNo);
        setCourses(courseIds);
    }

    public Lecturer(){
        super();
    }


    public Collection<String> getCourseIds() {
        return courseIds;
    }

    public void setCourses(Collection<String> courseIds) {
        this.courseIds = courseIds;
    }

    public void addTeachingCourse(String courseId){
        courseIds.add(courseId);
    }

    @JsonIgnore
    public ArrayList<Course> getCourses(){
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Course> allCourses = (ArrayList<Course>) Department.getInstance().getAllCourses();

        for(Course course : allCourses){
            if(this.getCourseIds().contains(course.getCourseCode())){
                courses.add(course);
            }
        }
        return courses;
    }

    @Override
    public String toString() {
        return "Lecturer{} " + super.toString();
    }
}
