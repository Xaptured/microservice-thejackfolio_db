/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.models;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * The class type Organization.
 */
@Component
public class Organization implements Serializable {

    private String name;
    private Date fromDate;
    private Date toDate;

    /**
     * Instantiates a new Organization.
     */
    public Organization() {
    }

    /**
     * Instantiates a new Organization.
     *
     * @param name     the name
     * @param fromDate the from date
     * @param toDate   the to date
     */
    public Organization(String name, Date fromDate, Date toDate) {
        this.name = name;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(name, that.name) && Objects.equals(fromDate, that.fromDate) && Objects.equals(toDate, that.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fromDate, toDate);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
