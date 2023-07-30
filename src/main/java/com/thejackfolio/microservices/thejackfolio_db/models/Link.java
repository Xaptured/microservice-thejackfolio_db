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
 * The class type Link.
 */
@Component
public class Link implements Serializable {

    private String linkedinLink;
    private String githubLink;
    private String youtubeLink;
    private String instagramLink;

    /**
     * Instantiates a new Link.
     */
    public Link() {
    }

    /**
     * Instantiates a new Link.
     *
     * @param linkedinLink  the linkedin link
     * @param githubLink    the github link
     * @param youtubeLink   the youtube link
     * @param instagramLink the instagram link
     */
    public Link(String linkedinLink, String githubLink, String youtubeLink, String instagramLink) {
        this.linkedinLink = linkedinLink;
        this.githubLink = githubLink;
        this.youtubeLink = youtubeLink;
        this.instagramLink = instagramLink;
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
        Link link = (Link) o;
        return Objects.equals(linkedinLink, link.linkedinLink) && Objects.equals(githubLink, link.githubLink) && Objects.equals(youtubeLink, link.youtubeLink) && Objects.equals(instagramLink, link.instagramLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkedinLink, githubLink, youtubeLink, instagramLink);
    }

    @Override
    public String toString() {
        return "Link{" +
                "linkedinLink='" + linkedinLink + '\'' +
                ", githubLink='" + githubLink + '\'' +
                ", youtubeLink='" + youtubeLink + '\'' +
                ", instagramLink='" + instagramLink + '\'' +
                '}';
    }
}
