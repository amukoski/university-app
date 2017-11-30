package com.university.www.dto;

import com.university.www.model.Student;

import java.io.Serializable;

public class StudentDTO implements Serializable {

    private String username;
    private Long indexNumber;
    private String firstName;
    private String lastName;
    private Long enrollID;

    public StudentDTO() {
    }

    public StudentDTO(Student student) {
        this.username = student.getUsername();
        this.indexNumber = student.getIndexNumber();
        this.firstName = student.getFirstName();
        this.lastName= student.getLastName();
    }

    public StudentDTO(String username, Long indexNumber, String firstName, String lastName) {
        this.username = username;
        this.indexNumber = indexNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(Long indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getEnrollID() {
        return enrollID;
    }

    public void setEnrollID(Long enrollID) {
        this.enrollID = enrollID;
    }
}
