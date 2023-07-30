/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

/**
 * The class type Personal details.
 */
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

    /**
     * Instantiates a new Personal details.
     */
    public PersonalDetails() {
    }

    /**
     * Instantiates a new Personal details.
     *
     * @param id            the id
     * @param firstName     the first name
     * @param middleName    the middle name
     * @param lastName      the last name
     * @param email         the email
     * @param dateOfBirth   the date of birth
     * @param flatNumber    the flat number
     * @param society       the society
     * @param city          the city
     * @param pinCode       the pin code
     * @param state         the state
     * @param country       the country
     * @param phoneNumber   the phone number
     * @param collegeName   the college name
     * @param branch        the branch
     * @param fromDate      the from date
     * @param toDate        the to date
     * @param linkedinLink  the linkedin link
     * @param githubLink    the github link
     * @param youtubeLink   the youtube link
     * @param instagramLink the instagram link
     */
    public PersonalDetails(Integer id, String firstName, String middleName, String lastName, String email, String dateOfBirth, String flatNumber, String society, String city, Integer pinCode, String state, String country, String phoneNumber, String collegeName, String branch, Date fromDate, Date toDate, String linkedinLink, String githubLink, String youtubeLink, String instagramLink) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets middle name.
     *
     * @return the middle name
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets middle name.
     *
     * @param middleName the middle name
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets flat number.
     *
     * @return the flat number
     */
    public String getFlatNumber() {
        return flatNumber;
    }

    /**
     * Sets flat number.
     *
     * @param flatNumber the flat number
     */
    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    /**
     * Gets society.
     *
     * @return the society
     */
    public String getSociety() {
        return society;
    }

    /**
     * Sets society.
     *
     * @param society the society
     */
    public void setSociety(String society) {
        this.society = society;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets pin code.
     *
     * @return the pin code
     */
    public Integer getPinCode() {
        return pinCode;
    }

    /**
     * Sets pin code.
     *
     * @param pinCode the pin code
     */
    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets college name.
     *
     * @return the college name
     */
    public String getCollegeName() {
        return collegeName;
    }

    /**
     * Sets college name.
     *
     * @param collegeName the college name
     */
    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    /**
     * Gets branch.
     *
     * @return the branch
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Sets branch.
     *
     * @param branch the branch
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * Gets from date.
     *
     * @return the from date
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * Sets from date.
     *
     * @param fromDate the from date
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * Gets to date.
     *
     * @return the to date
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * Sets to date.
     *
     * @param toDate the to date
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
     * Gets linkedin link.
     *
     * @return the linkedin link
     */
    public String getLinkedinLink() {
        return linkedinLink;
    }

    /**
     * Sets linkedin link.
     *
     * @param linkedinLink the linkedin link
     */
    public void setLinkedinLink(String linkedinLink) {
        this.linkedinLink = linkedinLink;
    }

    /**
     * Gets github link.
     *
     * @return the github link
     */
    public String getGithubLink() {
        return githubLink;
    }

    /**
     * Sets github link.
     *
     * @param githubLink the github link
     */
    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    /**
     * Gets youtube link.
     *
     * @return the youtube link
     */
    public String getYoutubeLink() {
        return youtubeLink;
    }

    /**
     * Sets youtube link.
     *
     * @param youtubeLink the youtube link
     */
    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    /**
     * Gets instagram link.
     *
     * @return the instagram link
     */
    public String getInstagramLink() {
        return instagramLink;
    }

    /**
     * Sets instagram link.
     *
     * @param instagramLink the instagram link
     */
    public void setInstagramLink(String instagramLink) {
        this.instagramLink = instagramLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalDetails that = (PersonalDetails) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(middleName, that.middleName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(flatNumber, that.flatNumber) && Objects.equals(society, that.society) && Objects.equals(city, that.city) && Objects.equals(pinCode, that.pinCode) && Objects.equals(state, that.state) && Objects.equals(country, that.country) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(collegeName, that.collegeName) && Objects.equals(branch, that.branch) && Objects.equals(fromDate, that.fromDate) && Objects.equals(toDate, that.toDate) && Objects.equals(linkedinLink, that.linkedinLink) && Objects.equals(githubLink, that.githubLink) && Objects.equals(youtubeLink, that.youtubeLink) && Objects.equals(instagramLink, that.instagramLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, email, dateOfBirth, flatNumber, society, city, pinCode, state, country, phoneNumber, collegeName, branch, fromDate, toDate, linkedinLink, githubLink, youtubeLink, instagramLink);
    }

    @Override
    public String toString() {
        return "PersonalDetails{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
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
