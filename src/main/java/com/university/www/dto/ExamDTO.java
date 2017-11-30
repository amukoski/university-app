package com.university.www.dto;

import java.io.Serializable;

public class ExamDTO implements Serializable {
    private String code;
    private String course;
    private String session;
    private Integer semesterNumber;
    private Double ektc;
    private Integer grade;

    public ExamDTO() {
    }

    public ExamDTO(String code, String course, String session, Integer semesterNumber, Double ektc, Integer grade) {
        this.code = code;
        this.course = course;
        this.session = session;
        this.semesterNumber = semesterNumber;
        this.ektc = ektc;
        this.grade = grade;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
