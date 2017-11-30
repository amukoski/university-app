package com.university.www.dto;

import com.university.www.model.Professor;

import java.io.Serializable;

public class ProfessorDTO implements Serializable {

    private String username;
    private String name;

    public ProfessorDTO() {
    }

    public ProfessorDTO(Professor professor){
        this.username = professor.getUsername();
        this.name = professor.getFirstName() + " " + professor.getLastName();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
