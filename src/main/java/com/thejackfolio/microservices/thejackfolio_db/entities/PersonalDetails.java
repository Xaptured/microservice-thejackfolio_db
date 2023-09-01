/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "personal_details")
public class PersonalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "description")
    private String description;
    @Column(name = "email")
    private String email;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Column(name = "flat_number")
    private String flatNumber;
    @Column(name = "society")
    private String society;
    @Column(name = "city")
    private String city;
    @Column(name = "pin_code")
    private Integer pinCode;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "college_name")
    private String collegeName;
    @Column(name = "branch")
    private String branch;
    @Column(name = "from_date")
    private Date fromDate;
    @Column(name = "to_date")
    private  Date toDate;
    @Column(name = "linkedin_link")
    private String linkedinLink;
    @Column(name = "github_link")
    private String githubLink;
    @Column(name = "youtube_link")
    private String youtubeLink;
    @Column(name = "instagram_link")
    private String instagramLink;

    public PersonalDetails() {
    }

    public PersonalDetails(Integer id, String firstName, String middleName, String lastName, String description, String email, String dateOfBirth, String flatNumber, String society, String city, Integer pinCode, String state, String country, String phoneNumber, String collegeName, String branch, Date fromDate, Date toDate, String linkedinLink, String githubLink, String youtubeLink, String instagramLink) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.description = description;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.flatNumber = flatNumber;
        this.society = society;
        this.city = city;
        this.pinCode = pinCode;
        this.state = state;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.collegeName = collegeName;
        this.branch = branch;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.linkedinLink = linkedinLink;
        this.githubLink = githubLink;
        this.youtubeLink = youtubeLink;
        this.instagramLink = instagramLink;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getLinkedinLink() {
        return linkedinLink;
    }

    public void setLinkedinLink(String linkedinLink) {
        this.linkedinLink = linkedinLink;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getInstagramLink() {
        return instagramLink;
    }

    public void setInstagramLink(String instagramLink) {
        this.instagramLink = instagramLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalDetails that = (PersonalDetails) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(middleName, that.middleName) && Objects.equals(lastName, that.lastName) && Objects.equals(description, that.description) && Objects.equals(email, that.email) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(flatNumber, that.flatNumber) && Objects.equals(society, that.society) && Objects.equals(city, that.city) && Objects.equals(pinCode, that.pinCode) && Objects.equals(state, that.state) && Objects.equals(country, that.country) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(collegeName, that.collegeName) && Objects.equals(branch, that.branch) && Objects.equals(fromDate, that.fromDate) && Objects.equals(toDate, that.toDate) && Objects.equals(linkedinLink, that.linkedinLink) && Objects.equals(githubLink, that.githubLink) && Objects.equals(youtubeLink, that.youtubeLink) && Objects.equals(instagramLink, that.instagramLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, description, email, dateOfBirth, flatNumber, society, city, pinCode, state, country, phoneNumber, collegeName, branch, fromDate, toDate, linkedinLink, githubLink, youtubeLink, instagramLink);
    }

    @Override
    public String toString() {
        return "PersonalDetails{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", flatNumber='" + flatNumber + '\'' +
                ", society='" + society + '\'' +
                ", city='" + city + '\'' +
                ", pinCode=" + pinCode +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", collegeName='" + collegeName + '\'' +
                ", branch='" + branch + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", linkedinLink='" + linkedinLink + '\'' +
                ", githubLink='" + githubLink + '\'' +
                ", youtubeLink='" + youtubeLink + '\'' +
                ", instagramLink='" + instagramLink + '\'' +
                '}';
    }
}
