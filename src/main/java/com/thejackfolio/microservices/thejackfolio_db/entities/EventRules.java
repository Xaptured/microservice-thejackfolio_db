/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "event_rules")
public class EventRules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "rule_number")
    private Integer ruleNumber;
    @Column(name = "event_id")
    private Integer eventId;
    @Column(name = "description")
    private String description;

    public EventRules() {
    }

    public EventRules(Integer id, Integer eventId, String description, Integer ruleNumber) {
        this.id = id;
        this.eventId = eventId;
        this.description = description;
        this.ruleNumber = ruleNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRuleNumber() {
        return ruleNumber;
    }

    public void setRuleNumber(Integer ruleNumber) {
        this.ruleNumber = ruleNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventRules that = (EventRules) o;
        return Objects.equals(id, that.id) && Objects.equals(ruleNumber, that.ruleNumber) && Objects.equals(eventId, that.eventId) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ruleNumber, eventId, description);
    }

    @Override
    public String toString() {
        return "EventRules{" +
                "id=" + id +
                ", ruleNumber=" + ruleNumber +
                ", eventId=" + eventId +
                ", description='" + description + '\'' +
                '}';
    }
}
