package Domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

public class Department {
    private static Collection<Person> allPeople = new ArrayList<Person>();
    private static Collection<Course> allCourses = new ArrayList<Course>();
    private static Collection<Registration> allRegistrations = new ArrayList<Registration>();

    private static Collection<Student> allStudents = new ArrayList<Student>();
    private static Collection<Lecturer> allLecturers = new ArrayList<Lecturer>();
    private static Collection<Advisor> allAdvisors = new ArrayList<Advisor>();

    static
    {
        Advisor advisor = new Advisor("Ilker","Keklik", null,"1",new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>());
        addAnObject(advisor);
        Advisor advisor2 = new Advisor("Burkay","Keklik", null,"2",new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>());
        addAnObject(advisor2);

        Student student = new Student("Kerim","Hasan",null,"1",new ArrayList<String>(),"1",new ArrayList<String>());
        addAnObject(student);
        Student student2 = new Student("Yusuf","Duman",null,"2",new ArrayList<String>(),"1",new ArrayList<String>());
        addAnObject(student2);

        Course course = new Course("Database","CSE3020",new ArrayList<String>(),"",new ArrayList<String>(),new ArrayList<String>());
        addAnObject(course);
        Course course2 = new Course("OOPP","CSE3070",new ArrayList<String>(),"",new ArrayList<String>(),new ArrayList<String>());
        addAnObject(course2);

        student.registerToNewCourse("CSE3070","1");
        System.out.println("Before proceed:"+student.getCourseCodes());
        advisor.proceedTheRegistration("1",RegistrationStatus.Confirmed);
        System.out.println("After proceed:"+student.getCourseCodes());
    }
    public Department() {
    }

    public static Collection<Registration> getAllRegistrations() {
        return allRegistrations;
    }

    public static void setAllRegistrations(Collection<Registration> allRegistrations) {
        Department.allRegistrations = allRegistrations;
    }

    public static Collection<Person> getAllPeople() {
        return allPeople;
    }

    public static void setAllPeople(Collection<Person> allPeople) {
        Department.allPeople = allPeople;
    }

    public static Collection<Course> getAllCourses() {
        return allCourses;
    }

    public static void setAllCourses(Collection<Course> allCourses) {
        Department.allCourses = allCourses;
    }

    public static Collection<Student> getAllStudents() {
        return allStudents;
    }

    public static void setAllStudents(Collection<Student> allStudents) {
        Department.allStudents = allStudents;
    }

    public static Collection<Lecturer> getAllLecturers() {
        return allLecturers;
    }

    public static void setAllLecturers(Collection<Lecturer> allLecturers) {
        Department.allLecturers = allLecturers;
    }

    public static Collection<Advisor> getAllAdvisors() {
        return allAdvisors;
    }

    public static void setAllAdvisors(Collection<Advisor> allAdvisors) {
        Department.allAdvisors = allAdvisors;
    }

    public static void addAnObject(Object object){
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

        if(!instanceCheck){
            System.out.println(object.getClass().getTypeName()+"Could not added");
        }

    }


}
