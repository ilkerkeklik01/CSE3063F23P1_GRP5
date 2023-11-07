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
    public static Lecturer getLecturerByStaffNo(String StaffNo){
        ArrayList<Lecturer> all =(ArrayList<Lecturer>) Department.getAllLecturers();

        Lecturer lecturer=null;
        for(Lecturer each :all){
            if(each.getStaffNo().equals(StaffNo)){
                lecturer=each;
                break;
            }
        }
        if(lecturer==null){
            System.out.println(StaffNo+" is not found");
        }
        return lecturer;
    }

    public Collection<String> getCourseIds() {
        return courseIds;
    }

    public void setCourses(Collection<String> courseIds) {
        this.courseIds = courseIds;
    }


}
