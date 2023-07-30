package com.thejackfolio.microservices.thejackfolio_db.models;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;


/**
 * The class type Personal details.
 */
@Component
public class PersonalDetails implements Serializable {

    private Name name;
    private String email;
    private Date dateOfBirth;
    private Address address;
    private String phoneNumber;
    private Education education;
    private Link link;

    /**
     * Instantiates a new Personal details.
     */
    public PersonalDetails() {
    }

    /**
     * Instantiates a new Personal details.
     *
     * @param name        the name
     * @param email       the email
     * @param dateOfBirth the date of birth
     * @param address     the address
     * @param phoneNumber the phone number
     * @param education   the education
     * @param link        the link
     */
    public PersonalDetails(Name name, String email, Date dateOfBirth, Address address, String phoneNumber, Education education, Link link) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.education = education;
        this.link = link;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public Name getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(Name name) {
        this.name = name;
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
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
        this.address = address;
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
     * Gets education.
     *
     * @return the education
     */
    public Education getEducation() {
        return education;
    }

    /**
     * Sets education.
     *
     * @param education the education
     */
    public void setEducation(Education education) {
        this.education = education;
    }

    /**
     * Gets link.
     *
     * @return the link
     */
    public Link getLink() {
        return link;
    }

    /**
     * Sets link.
     *
     * @param link the link
     */
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
