package com.university.www.model;

import javax.persistence.*;

@Entity
public class Enroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Course.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Course course;

    @ManyToOne(targetEntity = Semester.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Semester semester;

    @ManyToOne(targetEntity = Student.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Student student;

    @OneToOne(targetEntity = Exam.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "enroll")
    private Exam exam;

    public Enroll() {
    }

    public Enroll(Course course, Semester semester, Student student) {
        this.course = course;
        this.semester = semester;
        this.student = student;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
