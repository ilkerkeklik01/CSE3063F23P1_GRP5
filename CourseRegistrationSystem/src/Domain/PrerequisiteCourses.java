package Domain;

import java.util.ArrayList;

// Represents the prerequisite courses of a course in the course registration system.

public class PrerequisiteCourses extends Course {
    
    private ArrayList<Course> prerequisiteCourses;
    

    public PrerequisiteCourses(String courseName, String courseCode, ArrayList<String> lecturersNumbers, String courseSectionNo, ArrayList<String> studentNumbers, ArrayList<String> prerequisitesIds) {
        super(courseName, courseCode, lecturersNumbers, courseSectionNo, studentNumbers, prerequisitesIds);
    }

    public ArrayList<Course> getPrerequisiteCourses() {
        return prerequisiteCourses;
    }
    
    public void setPrerequisiteCourses(ArrayList<Course> prerequisiteCourses) {
        this.prerequisiteCourses = prerequisiteCourses;
    }
    
    // Adds a prerequisite course to the list of prerequisite courses for this course.
    public void addPrerequisiteCourse(Course course) {
        prerequisiteCourses.add(course);
    }
    
    // Removes a prerequisite course from the list of prerequisite courses for this course.
    public void removePrerequisiteCourse(Course course) {
        prerequisiteCourses.remove(course);
    }
    
    // Just to check the process.
    // Checks if a given course is a prerequisite course for this course.
    public boolean hasPrerequisiteCourse(Course course) {
        return prerequisiteCourses.contains(course);
    }
    
    // Just to check the process.
    // Checks if this course has any prerequisite courses.
    public boolean hasPrerequisiteCourses() {
        return !prerequisiteCourses.isEmpty();
    }


}