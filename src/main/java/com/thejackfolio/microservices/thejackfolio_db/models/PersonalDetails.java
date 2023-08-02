package com.thejackfolio.microservices.thejackfolio_db.models;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;


@Component
public class PersonalDetails implements Serializable {

    private Name name;
    private String email;
    private Date dateOfBirth;
    private Address address;
    private String phoneNumber;
    private Education education;
    private Link link;

    public PersonalDetails() {
    }

    public PersonalDetails(Name name, String email, Date dateOfBirth, Address address, String phoneNumber, Education education, Link link) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.education = education;
        this.link = link;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalDetails that = (PersonalDetails) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(address, that.address) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(education, that.education) && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, dateOfBirth, address, phoneNumber, education, link);
    }

    @Override
    public String toString() {
        return "PersonalDetails{" +
                "name=" + name +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", education=" + education +
                ", link=" + link +
                '}';
    }
}
