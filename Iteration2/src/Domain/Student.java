package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student extends Person {

    private String studentNo;
    private Collection<String> courseCodes;
    private String advisorNo;
    private Collection<String> registrationNumbers;
    private Transcript transcript;
    private int semester;
    private Collection<CourseSection> activeCourseSections;

    public Student(String FName, String LName, Date birthdate, String studentNo, Collection<String> courseCodes, String advisorNo,Collection<String> registrationNumbers, int semester) {
        super(FName, LName, birthdate);
        setStudentNo(studentNo);
        setCourseCodes(courseCodes);
        setAdvisorNo(advisorNo);
        setRegistrationNumbers(registrationNumbers);
        Department.getInstance().getAdvisorByStaffNo(advisorNo).getStudentNumbers().add(studentNo);
        this.transcript = new Transcript();
        setSemester(semester);
    }

    public Student(){

    };


    public void addCourseSection(CourseSection courseSection){
        if(activeCourseSections == null)
            activeCourseSections = new ArrayList<CourseSection>();
        activeCourseSections.add(courseSection);
    }

    private void courseNullCheck(Course course,String courseCode) throws Exception{
        if(course==null){
        throw  new Exception("There is no such a course: "+courseCode);
        }
    }

    private void prerequisitesControl(Course course) throws Exception{
        if(!this.transcript.getPassedCourseCodes().containsAll(course.getPrerequisitesIds())){
            throw new Exception("You have to pass the prerequisites of the course: "+course.getCourseCode()
            +"\nPrerequisites: " + course.getPrerequisitesIds()
            );
        }
    }

    private void cannotTakeMoreThanFiveCourses() throws Exception{
        if(studentHas5OrMoreCourses()){
            throw new Exception("You can not take more than 5 courses.\nEither you selected more than 5 courses or the number of active registrations and your courses count is more than 5.");
        }
    }


    private void cannotTakeMoreThan36Credits(Course course) throws Exception{
        int totalCredit = calculateTotalCreditTaken() + course.getCredit();
        if(totalCredit>36){
            throw new Exception("You cannot take more than 36 credits in one semester.");
        }
    }

    private void checkEligibilityForGraduationProject(Course askedCourse) throws Exception{
        if(askedCourse.getCourseCode().equals("CSE4297" )){
            int totalCredit = 0;
            int minCreditForGraduationProject = 165;

            for(Course course: this.getTranscript().getCompletedCourses()){
                if(course.getSemester() < 4){
                    totalCredit += course.getCredit();
                }
            }
            if(totalCredit < minCreditForGraduationProject ){
                throw new Exception("You cannot take graduation project. Your credits for first 6 semesters are below " + minCreditForGraduationProject);
            }
        }
    }

    private int calculateTotalCreditTaken(){
        Department department = Department.getInstance();

        int totalCreditTaken =0;
        for (String each:getCourseCodes()) {
        Course course =department.getCourseByCourseCode(each);
        totalCreditTaken+=course.getCredit();
        }
        return totalCreditTaken;
    }

    private void courseAlreadyCompletedCheck(Course course) throws Exception{
        if(this.getTranscript().getSuccessfullyCompletedCourseCodes().contains(course.getCourseCode())){
            throw new Exception("You have already completed this course: " +course.getCourseCode());
        }
    }

    private void checkIfEligibileForUpperClass(Course course) throws Exception{
        int minGanoForUpperClass = 3;

        if(course.getSemester() > this.getSemester() && this.getTranscript().getGano() < minGanoForUpperClass){
            throw new Exception("You are not eligible for upper class. Your GANO is less than " + minGanoForUpperClass);
        }
    }

    private void courseCurrentlyTakenCheck(Course course) throws Exception{
        if(this.getCourseCodes().contains(course.getCourseCode())){
            throw new Exception("You are currently taking this course: " + course.getCourseCode());
        }
    }

    public void checkCourseEligibility(String courseCode) throws Exception{
        Course course = Department.getInstance().getCourseByCourseCode(courseCode);

        courseNullCheck(course,courseCode);
        courseCurrentlyTakenCheck(course);
        courseAlreadyCompletedCheck(course);
        cannotTakeMoreThanFiveCourses();
        cannotTakeMoreThan36Credits(course);
        checkIfEligibileForUpperClass(course);
        prerequisitesControl(course);
        checkEligibilityForGraduationProject(course);
    }

    

    private void checkOverlapSections(Course course, String sectionNo) throws Exception{
        CourseSection courseSection = Department.getInstance().getCourseSectionBySectionNo(sectionNo);
        if(this.getActiveCourseSections() == null){
            return;
        }
        for (CourseSection each : this.getActiveCourseSections()) {
            for(String eachTime : each.getSectionTime()){
                if(courseSection.getSectionTime().contains(eachTime)){
                    throw new Exception("You have an overlap with the course: " + course.getCourseCode() + " and the course: " + each.getcourseCode());
                }
            }
                
        }
    }    

    private void checkQuota(Course course, String sectionNo) throws Exception{
        CourseSection courseSection = Department.getInstance().getCourseSectionBySectionNo(sectionNo);
        if(courseSection.isFull()){
            throw new Exception("The course section is full: " + course.getCourseCode() + " " + courseSection.getCourseSectionNo());
        }
    }

    private void checkCourseSectionEligibility(Course course, String sectionNo) throws Exception{
        checkOverlapSections(course, sectionNo);
        checkQuota(course, sectionNo);
    }



    public void registerToNewCourse(String courseCode,String newRegistrationNo, String sectionNo){
        Department department = Department.getInstance();  
        Course course = department.getCourseByCourseCode(courseCode);

        try{
            CourseSection courseSection = department.getCourseSectionBySectionNo(sectionNo);
            checkCourseSectionEligibility(course, sectionNo);

            Registration registration = new Registration(newRegistrationNo,this.getStudentNo(),this.getAdvisorNo(),courseCode,RegistrationStatus.Active, courseSection);
            this.getRegistrationNumbers().add(registration.getRegistrationNo());
            Advisor advisor = Department.getInstance().getAdvisorByStaffNo(getAdvisorNo());
            advisor.getRegistrationNumbers().add(registration.getRegistrationNo());
            Department.getInstance().getAllRegistrations().add(registration);

            System.out.println("Registration is successfully sent to the advisor");

        }catch (Exception e){
            System.out.println(e);
        }       
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getSemester() {
        return semester;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    @JsonIgnore
    public ArrayList<Course> getAvailableCourses(){

        Department department = Department.getInstance();
        Collection<Course> allCourses = department.getAllCourses();
        Collection<Course> availableCourses = new ArrayList<>();
        for (Course course : allCourses){
            if(!this.getTranscript().getSuccessfullyCompletedCourseCodes().contains(course.getCourseCode())){
                availableCourses.add(course);
            }
        }
        return (ArrayList<Course>)availableCourses;
    }


    public Collection<String> getRegistrationNumbers() {
        return registrationNumbers;
    }

    public void setRegistrationNumbers(Collection<String> registrationNumbers) {
        this.registrationNumbers = registrationNumbers;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public Collection<String> getCourseCodes() {
        return courseCodes;
    }

    public void setCourseCodes(Collection<String> courseCodes) {
        this.courseCodes = courseCodes;
    }

    public String getAdvisorNo() {
        return advisorNo;
    }

    public void setAdvisorNo(String advisorNo) {
        this.advisorNo = advisorNo;
    }

    public List<Registration> getRegistrations(){
        List<Registration> registrations;
        return  Department.getInstance().getAllRegistrations().stream().filter(r -> r.getStudentNo() == this.getStudentNo()).collect(Collectors.toList());
    }

    private boolean studentHas5OrMoreCourses(){
        if (this.courseCodes.size() + getRegistrations().stream().filter(r -> r.getStatus() == RegistrationStatus.Active).collect(Collectors.toList()).size() >= 5){
            return true;
        }
        return false;
    }

    public Collection<CourseSection> getActiveCourseSections() {
        return activeCourseSections;
    }

    public void setActiveCourseSections(Collection<CourseSection> activeCourseSections) {
        this.activeCourseSections = activeCourseSections;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentNo='" + studentNo + '\'' +
                ", courseCodes=" + courseCodes +
                ", advisorNo='" + advisorNo + '\'' +
                ", registrationNumbers=" + registrationNumbers +
                "} " + super.toString();
    }
}
