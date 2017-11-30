package com.university.www.dto;

import com.university.www.model.Course;
import com.university.www.model.Professor;

import java.io.Serializable;

public class CourseDTO implements Serializable {

    private Long id;
    private String code;
    private String name;
    private String description;
    private Character status;
    private String numberOfClasses;
    private Integer semesterNumber;
    private Double ektc;

    private String professor;

    public CourseDTO() {
    }

    public CourseDTO(Course course, Professor professor) {
        id = course.getId();
        code = course.getCode();
        name = course.getName();
        description = course.getDescription();
        status = course.getStatus();
        numberOfClasses = course.getNumberOfClasses();
        semesterNumber = course.getSemesterNumber();
        ektc = course.getEktc();

        this.professor = professor.getFirstName() + " " + professor.getLastName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public String getNumberOfClasses() {
        return numberOfClasses;
    }

    public void setNumberOfClasses(String numberOfClasses) {
        this.numberOfClasses = numberOfClasses;
    }

    public Integer getSemesterNumber() {
        return semesterNumber;
    }

    public void setSemesterNumber(Integer semesterNumber) {
        this.semesterNumber = semesterNumber;
    }

    public Double getEktc() {
        return ektc;
    }

    public void setEktc(Double ektc) {
        this.ektc = ektc;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
}
