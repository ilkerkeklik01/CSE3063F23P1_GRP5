package Data;

import Domain.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

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

    private String ADVISORSDTO_JSON_FILE ="advisorsdata.json";
    private String REGISTRATIONSDTO_JSON_FILE ="registrationsdata.json";
    private String STUDENTSDTO_JSON_FILE ="studentsdata.json";
    private String LECTURERSDTO_JSON_FILE ="lecturersdata.json";
    private String COURSESDTO_JSON_FILE ="coursesdata.json";


    // private String COURSE_SECTION_JSON_FILE = "courseSections.json";


    public  void saveData() {
        try {
            Department department = Department.getInstance();
            //  objectMapper.writeValue(new File(COURSE_SECTION_JSON_FILE), Department.getAllCourseSections());
            //objectMapper.writeValue(new File(ADVISORS_JSON_FILE), department.getAllAdvisors());
            //objectMapper.writeValue(new File(COURSES_JSON_FILE), department.getAllCourses());
            //objectMapper.writeValue(new File(REGISTRATIONS_JSON_FILE), department.getAllRegistrations());
            //objectMapper.writeValue(new File(STUDENTS_JSON_FILE), department.getAllStudents());
            //objectMapper.writeValue(new File(LECTURERS_JSON_FILE), department.getAllLecturers());


            // DTOS

            ArrayList<AdvisorDto> advisorDtos = new ArrayList<>();
            for(Advisor advisor : department.getAllAdvisors()){
                advisorDtos.add(new AdvisorDto(advisor));
            }
            objectMapper.writeValue(new File(ADVISORS_JSON_FILE), advisorDtos);


            ArrayList<CourseDto> courseDtos = new ArrayList<>();
            for(Course course : department.getAllCourses()){
                courseDtos.add(new CourseDto(course));
            }
            objectMapper.writeValue(new File(COURSES_JSON_FILE), courseDtos);

            ArrayList<RegistrationDto> registrationDtos = new ArrayList<>();
            for(Registration registration : department.getAllRegistrations()){
                registrationDtos.add(new RegistrationDto(registration));
            }


            objectMapper.writeValue(new File(REGISTRATIONS_JSON_FILE), registrationDtos);

            ArrayList<StudentDto> studentDtos = new ArrayList<>();
            for(Student student : department.getAllStudents()){
                studentDtos.add(new StudentDto(student));
            }
            objectMapper.writeValue(new File(STUDENTS_JSON_FILE), studentDtos);

            ArrayList<LecturerDto> lecturerDtos = new ArrayList<>();
            for(Lecturer lecturer : department.getAllLecturers()){
                lecturerDtos.add(new LecturerDto(lecturer));
            }
            objectMapper.writeValue(new File(LECTURERS_JSON_FILE), lecturerDtos);





            writeTranscriptFiles();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        try {
            Department department = Department.getInstance();

            //  Department.setAllCourseSections(objectMapper.readValue(new File(COURSE_SECTION_JSON_FILE), new TypeReference<Collection<CourseSection>>() {}));
            //department.setAllAdvisors(objectMapper.readValue(new File(ADVISORS_JSON_FILE), new TypeReference<Collection<Advisor>>() {}));
            //department.setAllCourses(objectMapper.readValue(new File(COURSES_JSON_FILE), new TypeReference<Collection<Course>>() {}));
            //department.setAllRegistrations(objectMapper.readValue(new File(REGISTRATIONS_JSON_FILE), new TypeReference<Collection<Registration>>() {}));
            //department.setAllStudents(objectMapper.readValue(new File(STUDENTS_JSON_FILE), new TypeReference<Collection<Student>>() {}));
            //department.setAllLecturers(objectMapper.readValue(new File(LECTURERS_JSON_FILE), new TypeReference<Collection<Lecturer>>() {}));



            Collection<AdvisorDto> advisorDtos = objectMapper.readValue(new File(ADVISORS_JSON_FILE), new TypeReference<Collection<AdvisorDto>>() {});
            Collection<CourseDto> courseDtos =  objectMapper.readValue(new File(COURSES_JSON_FILE), new TypeReference<Collection<CourseDto>>() {});
            Collection<RegistrationDto> registrationDtos =  objectMapper.readValue(new File(REGISTRATIONS_JSON_FILE), new TypeReference<Collection<RegistrationDto>>() {});
            Collection<StudentDto> studentDtos =  objectMapper.readValue(new File(STUDENTS_JSON_FILE), new TypeReference<Collection<StudentDto>>() {});
            Collection<LecturerDto> lecturerDtos = objectMapper.readValue(new File(LECTURERS_JSON_FILE), new TypeReference<Collection<LecturerDto>>() {});

            InitializeDomainObjects(studentDtos,lecturerDtos,courseDtos,registrationDtos,advisorDtos);


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

    private void InitializeDomainObjects(Collection<StudentDto> studentDtos, Collection<LecturerDto> lecturerDtos, Collection<CourseDto> courseDtos, Collection<RegistrationDto> registrationDtos, Collection<AdvisorDto> advisorDtos){
        ArrayList<Student> students =  initializeStudents(studentDtos);
        ArrayList<Lecturer> lecturers = initializeLecturers(lecturerDtos);
        ArrayList<Course> courses = initializeCourses(courseDtos);
        ArrayList<Registration> registrations = initializeRegistrations(registrationDtos);
        ArrayList<Advisor> advisors = initializeAdvisors(advisorDtos);


        for(Student student: students){
            StudentDto studentDto = studentDtos.stream().filter(s-> s.studentNo == student.getStudentNo()).collect(Collectors.toList()).get(0);
            try{

                ArrayList<Course> studentCourses = (ArrayList<Course>) courses.stream().filter(c -> studentDto.courseCodes.contains(c.getCourseCode())).collect(Collectors.toList());
                student.setCourses(studentCourses);
            }catch (Exception ex){

            }

            try{
                ArrayList<Registration> studentRegistration = (ArrayList<Registration>) registrations.stream().filter(r -> studentDto.registrationNumbers.contains(r.getRegistrationNo())).collect(Collectors.toList());
                student.setRegistrations(studentRegistration);
            }catch (Exception ex){

            }

        }

        for (Lecturer lecturer: lecturers){
            try{
                LecturerDto lecturerDto = lecturerDtos.stream().filter(l->l.staffNo == lecturer.getStaffNo()).collect(Collectors.toList()).get(0);
                ArrayList<Course> lecturerCourses = (ArrayList<Course>) courses.stream().filter(c -> lecturerDto.courseCodes.contains(c.getCourseCode())).collect(Collectors.toList());
                lecturer.setCourses(lecturerCourses);
            }catch (Exception ex){

            }

        }

        for(Course course : courses){
            CourseDto courseDto = courseDtos.stream().filter(c-> c.courseCode == course.getCourseCode()).collect(Collectors.toList()).get(0);

            try{
                ArrayList<Lecturer> courseLecturers = (ArrayList<Lecturer>) lecturers.stream().filter(l -> courseDto.lecturersNumbers.contains(l.getStaffNo())).collect(Collectors.toList());
                course.setLecturers(courseLecturers);
            }catch (Exception ex){

            }
            try{
                ArrayList<Student> courseStudents = (ArrayList<Student>) students.stream().filter(s -> courseDto.studentNumbers.contains(s.getStudentNo())).collect(Collectors.toList());
                course.setStudents(courseStudents);
            }catch (Exception ex){

            }
        }

        for (Registration registration : registrations){
            RegistrationDto registrationDto = registrationDtos.stream().filter(r-> r.registrationNo == registration.getRegistrationNo()).collect(Collectors.toList()).get(0);

            try{
                Advisor advisor = advisors.stream().filter(a-> a.getRegistrationNumbers().contains(registrationDto.registrationNo)).collect(Collectors.toList()).get(0);
                registration.setAdvisor(advisor);
            }catch (Exception ex){

            }

            try{
                Student student = students.stream().filter(s-> s.getRegistrationNumbers().contains(registrationDto.registrationNo)).collect(Collectors.toList()).get(0);
                registration.setStudent(student);
            }catch (Exception ex){

            }

            try{
                Course course = courses.stream().filter(c-> c.getCourseCode() ==registrationDto.courseCode).collect(Collectors.toList()).get(0);
                registration.setCourse(course);
            }catch (Exception ex){

            }


        }

        for (Advisor advisor : advisors ){
            AdvisorDto advisorDto = advisorDtos.stream().filter(a -> a.staffNo == advisor.getStaffNo()).collect(Collectors.toList()).get(0);

            try{
                ArrayList<Course> advisorCourses = (ArrayList<Course>) courses.stream().filter(c -> advisorDto.courseCodes.contains(c.getCourseCode())).collect(Collectors.toList());
                advisor.setCourses(advisorCourses);
            }catch (Exception ex){

            }

            try{
                ArrayList<Student> advisorStudents = (ArrayList<Student>) students.stream().filter(s -> advisorDto.studentNumbers.contains(s.getStudentNo())).collect(Collectors.toList());
                advisor.setStudents(advisorStudents);
            }catch (Exception ex){

            }
            try{
                ArrayList<Registration> advisorRegistrations = (ArrayList<Registration>) registrations.stream().filter(r -> advisorDto.registrationNumbers.contains(r.getRegistrationNo())).collect(Collectors.toList());
                advisor.setRegistrations(advisorRegistrations);
            }catch (Exception ex){

            }



        }

        Department department = Department.getInstance();
        department.setAllAdvisors(advisors);
        department.setAllCourses(courses);
        department.setAllLecturers(lecturers);
        department.setAllRegistrations(registrations);
        department.setAllStudents(students);























    }

    private ArrayList<Student> initializeStudents(Collection<StudentDto> studentDtos){
        ArrayList<Student> students = new ArrayList<>();
        for(StudentDto studentDto : studentDtos){
            Student student = new Student(studentDto.fName,studentDto.lName,studentDto.birthdate,studentDto.studentNo,studentDto.advisorNo);
            students.add(student);
        }
        return students;
    }

    private ArrayList<Lecturer> initializeLecturers(Collection<LecturerDto> lecturerDtos){
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        for(LecturerDto lecturerDto : lecturerDtos){
            Lecturer lecturer = new Lecturer(lecturerDto.fName,lecturerDto.lName,lecturerDto.birthdate,lecturerDto.staffNo);
            lecturers.add(lecturer);
        }
        return lecturers;
    }

    private ArrayList<Course> initializeCourses(Collection<CourseDto> courseDtos){
        ArrayList<Course> courses = new ArrayList<>();
        for(CourseDto courseDto : courseDtos){
            Course course = new Course(courseDto.courseName,courseDto.courseCode,courseDto.prerequisitesIds,courseDto.credit);
            courses.add(course);
        }
        return courses;
    }

    private ArrayList<Registration> initializeRegistrations(Collection<RegistrationDto> registrationDtos){
        ArrayList<Registration> registrations = new ArrayList<>();
        for(RegistrationDto registrationDto : registrationDtos){
            Registration registration = new Registration(registrationDto.registrationNo,registrationDto.status);
            registrations.add(registration);
        }
        return registrations;
    }

    private ArrayList<Advisor> initializeAdvisors(Collection<AdvisorDto> advisorDtos){
        ArrayList<Advisor> advisors = new ArrayList<>();
        for(AdvisorDto advisorDto : advisorDtos){
            Advisor advisor = new Advisor(advisorDto.fName,advisorDto.lName,advisorDto.birthdate,advisorDto.staffNo);
            advisors.add(advisor);
        }
        return advisors;
    }







}