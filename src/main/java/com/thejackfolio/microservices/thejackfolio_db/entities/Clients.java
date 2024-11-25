///*
// * Copyright (c) 2023.
// * Created this for the project called "TheJackFolio"
// * All right reserved by Jack
// */
//
//package com.thejackfolio.microservices.thejackfolio_db.entities;
//
//import jakarta.persistence.*;
//
//import java.util.Objects;
//
//@Entity
//@Table(name = "clients")
//public class Clients {
//
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    private Integer id;
//    @Id
//    @Column(name = "email")
//    private String email;
//    @Column(name = "password")
//    private String password;
//    @Column(name = "verified")
//    private boolean verified;
//    @Column(name = "first_name")
//    private String firstName;
//    @Column(name = "middle_name")
//    private String middleName;
//    @Column(name = "last_name")
//    private String lastName;
//    @Column(name = "date_of_birth")
//    private String dateOfBirth;
//    @Column(name = "flat_number")
//    private String flatNumber;
//    @Column(name = "society")
//    private String society;
//    @Column(name = "city")
//    private String city;
//    @Column(name = "pin_code")
//    private Integer pinCode;
//    @Column(name = "state")
//    private String state;
//    @Column(name = "country")
//    private String country;
//    @Column(name = "phone_number")
//    private String phoneNumber;
//
//    public Clients() {
//    }
//
//    public Clients(Integer id, String email, String password, boolean verified, String firstName, String middleName, String lastName, String dateOfBirth, String flatNumber, String society, String city, Integer pinCode, String state, String country, String phoneNumber) {
//        this.id = id;
//        this.email = email;
//        this.password = password;
//        this.verified = verified;
//        this.firstName = firstName;
//        this.middleName = middleName;
//        this.lastName = lastName;
//        this.dateOfBirth = dateOfBirth;
//        this.flatNumber = flatNumber;
//        this.society = society;
//        this.city = city;
//        this.pinCode = pinCode;
//        this.state = state;
//        this.country = country;
//        this.phoneNumber = phoneNumber;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public boolean isVerified() {
//        return verified;
//    }
//
//    public void setVerified(boolean verified) {
//        this.verified = verified;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getMiddleName() {
//        return middleName;
//    }
//
//    public void setMiddleName(String middleName) {
//        this.middleName = middleName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(String dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    public String getFlatNumber() {
//        return flatNumber;
//    }
//
//    public void setFlatNumber(String flatNumber) {
//        this.flatNumber = flatNumber;
//    }
//
//    public String getSociety() {
//        return society;
//    }
//
//    public void setSociety(String society) {
//        this.society = society;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public Integer getPinCode() {
//        return pinCode;
//    }
//
//    public void setPinCode(Integer pinCode) {
//        this.pinCode = pinCode;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Clients clients = (Clients) o;
//        return verified == clients.verified && Objects.equals(id, clients.id) && Objects.equals(email, clients.email) && Objects.equals(password, clients.password) && Objects.equals(firstName, clients.firstName) && Objects.equals(middleName, clients.middleName) && Objects.equals(lastName, clients.lastName) && Objects.equals(dateOfBirth, clients.dateOfBirth) && Objects.equals(flatNumber, clients.flatNumber) && Objects.equals(society, clients.society) && Objects.equals(city, clients.city) && Objects.equals(pinCode, clients.pinCode) && Objects.equals(state, clients.state) && Objects.equals(country, clients.country) && Objects.equals(phoneNumber, clients.phoneNumber);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, email, password, verified, firstName, middleName, lastName, dateOfBirth, flatNumber, society, city, pinCode, state, country, phoneNumber);
//    }
//
//    @Override
//    public String toString() {
//        return "Clients{" +
//                "id=" + id +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", verified=" + verified +
//                ", firstName='" + firstName + '\'' +
//                ", middleName='" + middleName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", dateOfBirth=" + dateOfBirth +
//                ", flatNumber='" + flatNumber + '\'' +
//                ", society='" + society + '\'' +
//                ", city='" + city + '\'' +
//                ", pinCode=" + pinCode +
//                ", state='" + state + '\'' +
//                ", country='" + country + '\'' +
//                ", phoneNumber='" + phoneNumber + '\'' +
//                '}';
//    }
//}
