/*
 * Copyright (c) 2025.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "advertisements")
public class AdvertisementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String advertiserName;

    @Column(nullable = false)
    private String imagePath;

    @Column(nullable = false)
    private String targetUrl;

    @Column(nullable = false)
    private boolean active;

    @Column(length = 255)
    private String altText;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdvertiserName() {
        return advertiserName;
    }

    public void setAdvertiserName(String advertiserName) {
        this.advertiserName = advertiserName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    @Override
    public String toString() {
        return "AdvertisementEntity{" +
                "id=" + id +
                ", advertiserName='" + advertiserName + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", targetUrl='" + targetUrl + '\'' +
                ", active=" + active +
                ", altText='" + altText + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdvertisementEntity that = (AdvertisementEntity) o;
        return active == that.active && Objects.equals(id, that.id) && Objects.equals(advertiserName, that.advertiserName) && Objects.equals(imagePath, that.imagePath) && Objects.equals(targetUrl, that.targetUrl) && Objects.equals(altText, that.altText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, advertiserName, imagePath, targetUrl, active, altText);
    }
}
