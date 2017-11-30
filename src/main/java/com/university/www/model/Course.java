package com.university.www.model;

import javax.persistence.*;

@Entity
public class Course implements Comparable<Course> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String description;
    private Character status;
    private String numberOfClasses;
    private Integer semesterNumber;
    private Double ektc;

    @ManyToOne(targetEntity = Administrator.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Administrator createdBy;

    public Course() {
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

    public Administrator getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Administrator createdBy) {
        this.createdBy = createdBy;
    }

    public Double getEktc() {
        return ektc;
    }

    public void setEktc(Double ektc) {
        this.ektc = ektc;
    }

    @Override
    public int compareTo(Course o) {
        return this.id.compareTo(o.getId());
    }
}
