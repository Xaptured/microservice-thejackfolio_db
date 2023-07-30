/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.models;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

/**
 * The class type Address.
 */
@Component
public class Address implements Serializable {

    private String flatNumber;
    private String society;
    private String city;
    private Integer pinCode;
    private String state;
    private String country;

    /**
     * Instantiates a new Address.
     */
    public Address() {
    }

    /**
     * Instantiates a new Address.
     *
     * @param flatNumber the flat number
     * @param society    the society
     * @param city       the city
     * @param pinCode    the pin code
     * @param state      the state
     * @param country    the country
     */
    public Address(String flatNumber, String society, String city, Integer pinCode, String state, String country) {
        this.flatNumber = flatNumber;
        this.society = society;
        this.city = city;
        this.pinCode = pinCode;
        state = state;
        this.country = country;
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
    public String getstate() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setstate(String state) {
        state = state;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(flatNumber, address.flatNumber) && Objects.equals(society, address.society) && Objects.equals(city, address.city) && Objects.equals(pinCode, address.pinCode) && Objects.equals(state, address.state) && Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flatNumber, society, city, pinCode, state, country);
    }

    @Override
    public String toString() {
        return "Address{" +
                "flatNumber='" + flatNumber + '\'' +
                ", society='" + society + '\'' +
                ", city='" + city + '\'' +
                ", pinCode=" + pinCode +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
