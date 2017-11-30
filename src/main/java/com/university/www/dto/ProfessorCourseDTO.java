package com.university.www.dto;

import com.university.www.model.Course;
import com.university.www.model.Semester;

import java.io.Serializable;
import java.util.List;

public class ProfessorCourseDTO implements Serializable {

    private Course course;
    private Semester semester;
    private List<StudentDTO> students;

    public ProfessorCourseDTO() {
    }

    public ProfessorCourseDTO(Course course, Semester semester, List<StudentDTO> students) {
        this.course = course;
        this.semester = semester;
        this.students = students;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }
}
