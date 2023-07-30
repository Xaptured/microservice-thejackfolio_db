/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * The class type Clients.
 */
@Entity
@Table(name = "clients")
public class Clients {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Id
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "verified")
    private boolean verified;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
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

    /**
     * Instantiates a new Clients.
     */
    public Clients() {
    }

    /**
     * Instantiates a new Clients.
     *
     * @param id          the id
     * @param email       the email
     * @param password    the password
     * @param verified    the verified
     * @param firstName   the first name
     * @param middleName  the middle name
     * @param lastName    the last name
     * @param dateOfBirth the date of birth
     * @param flatNumber  the flat number
     * @param society     the society
     * @param city        the city
     * @param pinCode     the pin code
     * @param state       the state
     * @param country     the country
     * @param phoneNumber the phone number
     */
    public Clients(Integer id, String email, String password, boolean verified, String firstName, String middleName, String lastName, String dateOfBirth, String flatNumber, String society, String city, Integer pinCode, String state, String country, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.verified = verified;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.flatNumber = flatNumber;
        this.society = society;
        this.city = city;
        this.pinCode = pinCode;
        this.state = state;
        this.country = country;
        this.phoneNumber = phoneNumber;
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
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Is verified boolean.
     *
     * @return the boolean
     */
    public boolean isVerified() {
        return verified;
    }

    /**
     * Sets verified.
     *
     * @param verified the verified
     */
    public void setVerified(boolean verified) {
        this.verified = verified;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clients clients = (Clients) o;
        return verified == clients.verified && Objects.equals(id, clients.id) && Objects.equals(email, clients.email) && Objects.equals(password, clients.password) && Objects.equals(firstName, clients.firstName) && Objects.equals(middleName, clients.middleName) && Objects.equals(lastName, clients.lastName) && Objects.equals(dateOfBirth, clients.dateOfBirth) && Objects.equals(flatNumber, clients.flatNumber) && Objects.equals(society, clients.society) && Objects.equals(city, clients.city) && Objects.equals(pinCode, clients.pinCode) && Objects.equals(state, clients.state) && Objects.equals(country, clients.country) && Objects.equals(phoneNumber, clients.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, verified, firstName, middleName, lastName, dateOfBirth, flatNumber, society, city, pinCode, state, country, phoneNumber);
    }

    @Override
    public String toString() {
        return "Clients{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", verified=" + verified +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", flatNumber='" + flatNumber + '\'' +
                ", society='" + society + '\'' +
                ", city='" + city + '\'' +
                ", pinCode=" + pinCode +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
