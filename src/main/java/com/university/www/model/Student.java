package com.university.www.model;

import javax.persistence.*;

@Entity
public class Student {

    @Id
    private String username;
    private String firstName;
    private String lastName;
    private String embg;
    private Long indexNumber;
    private String email;
    private Character gender;
    private String country;
    private String city;
    private String address;
    private String mobilePhone;
    private Double totalEktc;

    @MapsId
    @OneToOne(targetEntity = Account.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Account account;

    public Student() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmbg() {
        return embg;
    }

    public void setEmbg(String embg) {
        this.embg = embg;
    }

    public Long getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(Long indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Double getTotalEktc() {
        return totalEktc;
    }

    public void setTotalEktc(Double totalEktc) {
        this.totalEktc = totalEktc;
    }
}
