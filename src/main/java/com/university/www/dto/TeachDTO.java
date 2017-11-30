package com.university.www.dto;

import com.university.www.model.Teach;

import java.io.Serializable;

public class TeachDTO implements Serializable {

    private String professor;
    private Long course;

    public TeachDTO() {
    }

    public TeachDTO(Teach teach) {
        this.professor = teach.getProfessor().getFirstName() + " " + teach.getProfessor().getLastName();
        this.course = teach.getCourse().getId();
    }

    public TeachDTO(String professor, Long course) {
        this.professor = professor;
        this.course = course;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public Long getCourse() {
        return course;
    }

    public void setCourse(Long course) {
        this.course = course;
    }
}
