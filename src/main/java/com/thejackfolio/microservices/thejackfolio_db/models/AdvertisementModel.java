/*
 * Copyright (c) 2025.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.models;

import java.util.Objects;

public class AdvertisementModel {
    private String advertiserName;
    private String imagePath;
    private String targetUrl;
    private String altText;
    private boolean active;

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

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdvertisementModel that = (AdvertisementModel) o;
        return active == that.active && Objects.equals(advertiserName, that.advertiserName) && Objects.equals(imagePath, that.imagePath) && Objects.equals(targetUrl, that.targetUrl) && Objects.equals(altText, that.altText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(advertiserName, imagePath, targetUrl, altText, active);
    }

    @Override
    public String toString() {
        return "AdvertisementModel{" +
                "advertiserName='" + advertiserName + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", targetUrl='" + targetUrl + '\'' +
                ", altText='" + altText + '\'' +
                ", active=" + active +
                '}';
    }
}
