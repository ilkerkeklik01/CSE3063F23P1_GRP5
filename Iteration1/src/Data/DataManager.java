package Data;

import Domain.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

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
    

    public  void saveData() {
        try {
            Department department = Department.getInstance();
            //  objectMapper.writeValue(new File(COURSE_SECTION_JSON_FILE), Department.getAllCourseSections());
            objectMapper.writeValue(new File(ADVISORS_JSON_FILE), department.getAllAdvisors());
            objectMapper.writeValue(new File(COURSES_JSON_FILE), department.getAllCourses());
            objectMapper.writeValue(new File(REGISTRATIONS_JSON_FILE), department.getAllRegistrations());
            objectMapper.writeValue(new File(STUDENTS_JSON_FILE), department.getAllStudents());
            objectMapper.writeValue(new File(LECTURERS_JSON_FILE), department.getAllLecturers());

            writeTranscriptFiles();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
         try {
             Department department = Department.getInstance();

            //  Department.setAllCourseSections(objectMapper.readValue(new File(COURSE_SECTION_JSON_FILE), new TypeReference<Collection<CourseSection>>() {}));
            department.setAllAdvisors(objectMapper.readValue(new File(ADVISORS_JSON_FILE), new TypeReference<Collection<Advisor>>() {}));
            department.setAllCourses(objectMapper.readValue(new File(COURSES_JSON_FILE), new TypeReference<Collection<Course>>() {}));
            department.setAllRegistrations(objectMapper.readValue(new File(REGISTRATIONS_JSON_FILE), new TypeReference<Collection<Registration>>() {}));
            department.setAllStudents(objectMapper.readValue(new File(STUDENTS_JSON_FILE), new TypeReference<Collection<Student>>() {}));
            department.setAllLecturers(objectMapper.readValue(new File(LECTURERS_JSON_FILE), new TypeReference<Collection<Lecturer>>() {}));

            readTranscriptFiles();

        } catch (IOException e) {
            e.printStackTrace();
        }
        LoadDataToAbstarcts();
    }

    private void LoadDataToAbstarcts() {
        Department department = Department.getInstance();

        Collection<Staff> allStaffs = new ArrayList<>();
        allStaffs.addAll(department.getAllLecturers());
        department.setAllStaffs(allStaffs);

        Collection<Person> allPeople = new ArrayList<>();
        allPeople.addAll(department.getAllStudents());
        allPeople.addAll(department.getAllStaffs());
        department.setAllPeople(allPeople);
    }

    private void writeTranscriptFiles(){
        Department department = Department.getInstance();
        Collection<Student> students = department.getAllStudents();

        for(Student  student : students){
            writeTranscriptFileForStudent(student);
        }
    }

    private void writeTranscriptFileForStudent(Student student){
        String fileName = student.getStudentNo()+".json";
        try {
            objectMapper.writeValue(new File(fileName),createTranscriptDto(student));
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    private TranscriptDto createTranscriptDto(Student student){
        return new TranscriptDto(student);
    }

    private void readTranscriptFiles(){
        Department department = Department.getInstance();

        Collection<Student> students = department.getAllStudents();

        for(Student student : students){
            readTranscriptFileForStudent(student);
        }
    }

    private void readTranscriptFileForStudent(Student student){
        String fileName = student.getStudentNo() + ".json";
        try {
            TranscriptDto transcriptDto = objectMapper.readValue(new File(fileName), new TypeReference<TranscriptDto>() {});
            createTranscriptForStudent(student, transcriptDto);
        }catch (IOException e){
            e.printStackTrace();
        }




    }

    private void createTranscriptForStudent(Student student, TranscriptDto transcriptDto){
        Department department = Department.getInstance();
        Transcript transcript = student.getTranscript();
        for (GradeDto gradeDto : transcriptDto.grades){
            transcript.addGrade(gradeDto.courseCode, gradeDto.numGrade);
        }
    }



}