package com.university.www.model;

import javax.persistence.*;

@Entity
public class Teach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Course.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Course course;

    @ManyToOne(targetEntity = Semester.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Semester semester;

    @ManyToOne(targetEntity = Professor.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Professor professor;

    public Teach() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
