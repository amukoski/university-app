package com.university.www.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Exam {

    @Id
    private Long id;
    private String session;

    @Min(value = 6)
    @Max(value = 10)
    private Integer grade;

    @MapsId
    @OneToOne(targetEntity = Enroll.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Enroll enroll;

    public Exam() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Enroll getEnroll() {
        return enroll;
    }

    public void setEnroll(Enroll enroll) {
        this.enroll = enroll;
    }
}
