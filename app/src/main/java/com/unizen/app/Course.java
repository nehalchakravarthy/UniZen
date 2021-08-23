package com.unizen.app;

import java.io.Serializable;

public class Course implements Serializable {

    /**
     * Course model to add courses to course list dynamically
     **/

    public String courseName;
    public int courseCredits;
    public String grade;

    public Course(String courseName, int courseCredits, String grade) {
        // Constructor
        this.courseName = courseName;
        this.courseCredits = courseCredits;
        this.grade = grade;
    }

    public Course() {
        // Empty constructor
    }

    // Getter methods
    public String getCourseName() {
        return courseName;
    }

    public int getCourseCredits() {
        return courseCredits;
    }

    public String getGrade() {
        return grade;
    }

    // Setter methods
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseCredits(int courseCredits) {
        this.courseCredits = courseCredits;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
