package com.university.www.model;

import javax.persistence.*;

@Entity
public class Semester implements Comparable<Semester>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isSummer;
    private String academicYear;

    @ManyToOne(targetEntity = Administrator.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Administrator createdBy;

    public Semester() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getSummer() {
        return isSummer;
    }

    public void setSummer(Boolean summer) {
        isSummer = summer;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public Administrator getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Administrator createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public int compareTo(Semester toCompare) {
        return this.id.compareTo(toCompare.getId());
    }
}
