package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Lecturer extends Staff{

    private Collection<String> courseIds;

    public Lecturer(String FName, String LName, Date birthdate, String staffNo, Collection<String> courseIds) {
        super(FName, LName, birthdate, staffNo);
        setCourses(courseIds);
    }

    public Collection<String> getCourseIds() {
        return courseIds;
    }

    public void setCourses(Collection<String> courseIds) {
        this.courseIds = courseIds;
    }


}
