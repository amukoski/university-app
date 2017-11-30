package com.university.www.dto;

import com.university.www.model.Semester;

import java.io.Serializable;
import java.util.List;

public class SemesterDTO implements Serializable{

    private Semester semester;
    private List<CourseDTO> courses;

    public SemesterDTO() {
    }

    public SemesterDTO(Semester semester, List<CourseDTO> courses) {
        this.semester = semester;
        this.courses = courses;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }
}
