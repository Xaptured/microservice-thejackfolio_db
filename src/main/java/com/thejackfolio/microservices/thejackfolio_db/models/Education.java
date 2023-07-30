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
 * The class type Education.
 */
@Component
public class Education implements Serializable {

    private String collegeName;
    private String branch;
    private Date fromDate;
    private  Date toDate;

    /**
     * Instantiates a new Education.
     */
    public Education() {
    }

    /**
     * Instantiates a new Education.
     *
     * @param collegeName the college name
     * @param branch      the branch
     * @param fromDate    the from date
     * @param toDate      the to date
     */
    public Education(String collegeName, String branch, Date fromDate, Date toDate) {
        this.collegeName = collegeName;
        this.branch = branch;
        this.fromDate = fromDate;
        this.toDate = toDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Education education = (Education) o;
        return Objects.equals(collegeName, education.collegeName) && Objects.equals(branch, education.branch) && Objects.equals(fromDate, education.fromDate) && Objects.equals(toDate, education.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collegeName, branch, fromDate, toDate);
    }

    @Override
    public String toString() {
        return "Education{" +
                "collegeName='" + collegeName + '\'' +
                ", branch='" + branch + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
