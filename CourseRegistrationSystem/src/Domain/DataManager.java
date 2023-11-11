package Domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

    // THESE 2 LINES WILL BE ADDED TO THE TOP OF EACH CLASS THAT WILL BE STORED IN JSON FILES:
    // import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    // @JsonIgnoreProperties(ignoreUnknown = true)

public class DataManager {
    private ObjectMapper objectMapper = new ObjectMapper();
    private String COURSES_JSON_FILE = "courses.json";
    private String REGISTRATIONS_JSON_FILE = "registrations.json";
    private String STUDENTS_JSON_FILE = "students.json";
    private String LECTURERS_JSON_FILE = "lecturers.json";
    private String ADVISORS_JSON_FILE = "advisors.json";
   // private String COURSE_SECTION_JSON_FILE = "courseSections.json";
    

    public  void SaveData() {
        try {
          //  objectMapper.writeValue(new File(COURSE_SECTION_JSON_FILE), Department.getAllCourseSections());
            objectMapper.writeValue(new File(ADVISORS_JSON_FILE), Department.getAllAdvisors());
            objectMapper.writeValue(new File(COURSES_JSON_FILE), Department.getAllCourses());
            objectMapper.writeValue(new File(REGISTRATIONS_JSON_FILE), Department.getAllRegistrations());
            objectMapper.writeValue(new File(STUDENTS_JSON_FILE), Department.getAllStudents());
            objectMapper.writeValue(new File(LECTURERS_JSON_FILE), Department.getAllLecturers());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
         try {
          //  Department.setAllCourseSections(objectMapper.readValue(new File(COURSE_SECTION_JSON_FILE), new TypeReference<Collection<CourseSection>>() {}));
            Department.setAllAdvisors(objectMapper.readValue(new File(ADVISORS_JSON_FILE), new TypeReference<Collection<Advisor>>() {}));
            Department.setAllCourses(objectMapper.readValue(new File(COURSES_JSON_FILE), new TypeReference<Collection<Course>>() {}));
            Department.setAllRegistrations(objectMapper.readValue(new File(REGISTRATIONS_JSON_FILE), new TypeReference<Collection<Registration>>() {}));
            Department.setAllStudents(objectMapper.readValue(new File(STUDENTS_JSON_FILE), new TypeReference<Collection<Student>>() {}));
            Department.setAllLecturers(objectMapper.readValue(new File(LECTURERS_JSON_FILE), new TypeReference<Collection<Lecturer>>() {}));

        } catch (IOException e) {
            e.printStackTrace();
        }
        LoadDataToAbstarcts();
    }

    private void LoadDataToAbstarcts() {
        Collection<Staff> allStaffs = new ArrayList<>();
        allStaffs.addAll(Department.getAllLecturers());
        Department.setAllStaffs(allStaffs);

        Collection<Person> allPeople = new ArrayList<>();
        allPeople.addAll(Department.getAllStudents());
        allPeople.addAll(Department.getAllStaffs());
        Department.setAllPeople(allPeople);       
    }


}
