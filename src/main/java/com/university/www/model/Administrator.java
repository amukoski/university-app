package com.university.www.model;

import javax.persistence.*;

@Entity
public class Administrator {

    @Id
    private String username;

    private String firstName;
    private String lastName;
    private String email;
    private String mobilePhone;

    @MapsId
    @OneToOne(targetEntity = Account.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Account account;

    public Administrator() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
