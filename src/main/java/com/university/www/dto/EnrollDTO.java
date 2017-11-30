package com.university.www.dto;

import com.university.www.model.Course;
import com.university.www.model.Semester;
import com.university.www.model.Student;

import java.io.Serializable;
import java.util.List;

public class EnrollDTO implements Serializable {

    private Student student;
    private Semester semester;
    private List<Course> courses;

    public EnrollDTO() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
