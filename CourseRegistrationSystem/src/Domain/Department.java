package Domain;

import java.util.ArrayList;
import java.util.Collection;

public class Department {
    private Collection<Person> allPeople = new ArrayList<Person>();
    private Collection<Course> allCourses = new ArrayList<Course>();
    private Collection<Registration> allRegistrations = new ArrayList<Registration>();
    private Collection<Staff> allStaffs = new ArrayList<Staff>();
    private Collection<Student> allStudents = new ArrayList<Student>();
    private Collection<Lecturer> allLecturers = new ArrayList<Lecturer>();
    private Collection<Advisor> allAdvisors = new ArrayList<Advisor>();

    private static Department department = null;


    private Department() {
    }


    public Advisor getAdvisorByStaffNo(String StaffNo){
        ArrayList<Advisor> all =(ArrayList<Advisor>)getAllAdvisors();

        Advisor advisor=null;
        for(Advisor each :all){
            if(each.getStaffNo().equals(StaffNo)){
                advisor=each;
                break;
            }
        }
        if(advisor==null){
            System.out.println(StaffNo+" is not found");
        }
        return advisor;
    }
    public Course getCourseByCourseCode(String courseCode){
        ArrayList<Course> all =(ArrayList<Course>)getAllCourses();

        Course course=null;
        for(Course each :all){
            if(each.getCourseCode().equals(courseCode)){
                course=each;
                break;
            }
        }
        if(course==null){
            System.out.println(courseCode+" is not found");

        }
        return course;
    }
    public Lecturer getLecturerByStaffNo(String StaffNo){
        ArrayList<Lecturer> all =(ArrayList<Lecturer>)getAllLecturers();

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
    public Registration getRegistrationByRegistrationNo(String registrationNo){
        ArrayList<Registration> all =(ArrayList<Registration>)getAllRegistrations();

        Registration registration=null;
        for(Registration each :all){
            if(each.getRegistrationNo().equals(registrationNo)){
                registration=each;
                break;
            }
        }
        if(registration==null){
            System.out.println(registrationNo+" is not found");

        }
        return registration;
    }
    public Student getStudentByStudentNo(String studentNo){
        ArrayList<Student> all =(ArrayList<Student>)getAllStudents();

        Student student=null;
        for(Student each :all){
            if(each.getStudentNo().equals(studentNo)){
                student=each;
                break;
            }
        }
        if(student==null){
            System.out.println(studentNo+" is not found");
        }
        return student;
    }


    public static Department getInstance(){
        if(department==null){
            department = new Department();
        }
        return department;
    }


    public void addAnObject(Object object){


        boolean instanceCheck = false;
        if(object instanceof Person){
            allPeople.add((Person)object);
            instanceCheck=true;
        }
        if(object instanceof Course){
            allCourses.add((Course)object);
            instanceCheck=true;
        }
        if(object instanceof Student){
            allStudents.add((Student)object);
            instanceCheck=true;
        }
        if(object instanceof Lecturer){
            allLecturers.add((Lecturer)object);
            instanceCheck=true;
        }
        if(object instanceof Advisor){
            allAdvisors.add((Advisor) object);
            instanceCheck=true;
        }
        if(object instanceof Registration){
            allRegistrations.add((Registration) object);
        }
        if(object instanceof Staff){
            allStaffs.add((Staff) object);
        }

        if(!instanceCheck){
            System.out.println(object.getClass().getTypeName()+"Could not added");
        }
    }

    public Collection<Person> getAllPeople() {
        return allPeople;
    }

    public void setAllPeople(Collection<Person> allPeople) {
        this.allPeople = allPeople;
    }

    public Collection<Course> getAllCourses() {
        return allCourses;
    }

    public void setAllCourses(Collection<Course> allCourses) {
        this.allCourses = allCourses;
    }

    public Collection<Registration> getAllRegistrations() {
        return allRegistrations;
    }

    public void setAllRegistrations(Collection<Registration> allRegistrations) {
        this.allRegistrations = allRegistrations;
    }

    public Collection<Staff> getAllStaffs() {
        return allStaffs;
    }

    public void setAllStaffs(Collection<Staff> allStaffs) {
        this.allStaffs = allStaffs;
    }

    public Collection<Student> getAllStudents() {
        return allStudents;
    }

    public void setAllStudents(Collection<Student> allStudents) {
        this.allStudents = allStudents;
    }

    public Collection<Lecturer> getAllLecturers() {
        return allLecturers;
    }

    public void setAllLecturers(Collection<Lecturer> allLecturers) {
        this.allLecturers = allLecturers;
    }

    public Collection<Advisor> getAllAdvisors() {
        return allAdvisors;
    }

    public void setAllAdvisors(Collection<Advisor> allAdvisors) {
        this.allAdvisors = allAdvisors;
    }
}
