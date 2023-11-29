/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import com.thejackfolio.microservices.thejackfolio_db.enums.PartnerStatus;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "partners")
public class Partners {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "status")
    private PartnerStatus status;
    @Column(name = "logo_name")
    private String logoName;
    @Column(name = "logo_type")
    private String logoType;
    @Column(name = "logo_path")
    private String logoPath;
    @Column(name = "doc_name")
    private String documentName;
    @Column(name = "doc_type")
    private String documentType;
    @Column(name = "doc_path")
    private String documentPath;

    public Partners() {
    }

    public Partners(Integer id, String name, String email, PartnerStatus status, String logoName, String logoType, String logoPath, String documentName, String documentType, String documentPath) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
        this.logoName = logoName;
        this.logoType = logoType;
        this.logoPath = logoPath;
        this.documentName = documentName;
        this.documentType = documentType;
        this.documentPath = documentPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PartnerStatus getStatus() {
        return status;
    }

    public void setStatus(PartnerStatus status) {
        this.status = status;
    }

    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }

    public String getLogoType() {
        return logoType;
    }

    public void setLogoType(String logoType) {
        this.logoType = logoType;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partners partners = (Partners) o;
        return Objects.equals(id, partners.id) && Objects.equals(name, partners.name) && Objects.equals(email, partners.email) && status == partners.status && Objects.equals(logoName, partners.logoName) && Objects.equals(logoType, partners.logoType) && Objects.equals(logoPath, partners.logoPath) && Objects.equals(documentName, partners.documentName) && Objects.equals(documentType, partners.documentType) && Objects.equals(documentPath, partners.documentPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, status, logoName, logoType, logoPath, documentName, documentType, documentPath);
    }

    @Override
    public String toString() {
        return "Partners{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", logoName='" + logoName + '\'' +
                ", logoType='" + logoType + '\'' +
                ", logoPath='" + logoPath + '\'' +
                ", documentName='" + documentName + '\'' +
                ", documentType='" + documentType + '\'' +
                ", documentPath='" + documentPath + '\'' +
                '}';
    }
}
