/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import com.thejackfolio.microservices.thejackfolio_db.enums.EventType;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "event_details")
public class EventDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "event_id")
    private Integer eventId;
    @Column(name = "date")
    private Date date;
    @Column(name = "time")
    private Time time;
    @Column(name = "duration")
    private Time duration;
    @Column(name = "players_per_slot")
    private Integer playersPerSlot;
    @Column(name = "slot_count")
    private Integer slotCount;
    @Column(name = "remaining_slots")
    private Integer remainingSlots;
    @Column(name = "type")
    private EventType type;
    @Column(name = "prize_pool")
    private Float prizePool;

    public EventDetails() {
    }

    public EventDetails(Integer id, Integer eventId, Date date, Time time, Time duration, Integer playersPerSlot, Integer slotCount, Integer remainingSlots, EventType type, Float prizePool) {
        this.id = id;
        this.eventId = eventId;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.playersPerSlot = playersPerSlot;
        this.slotCount = slotCount;
        this.remainingSlots = remainingSlots;
        this.type = type;
        this.prizePool = prizePool;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Integer getPlayersPerSlot() {
        return playersPerSlot;
    }

    public void setPlayersPerSlot(Integer playersPerSlot) {
        this.playersPerSlot = playersPerSlot;
    }

    public Integer getSlotCount() {
        return slotCount;
    }

    public void setSlotCount(Integer slotCount) {
        this.slotCount = slotCount;
    }

    public Integer getRemainingSlots() {
        return remainingSlots;
    }

    public void setRemainingSlots(Integer remainingSlots) {
        this.remainingSlots = remainingSlots;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Float getPrizePool() {
        return prizePool;
    }

    public void setPrizePool(Float prizePool) {
        this.prizePool = prizePool;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventDetails that = (EventDetails) o;
        return Objects.equals(id, that.id) && Objects.equals(eventId, that.eventId) && Objects.equals(date, that.date) && Objects.equals(time, that.time) && Objects.equals(duration, that.duration) && Objects.equals(playersPerSlot, that.playersPerSlot) && Objects.equals(slotCount, that.slotCount) && Objects.equals(remainingSlots, that.remainingSlots) && type == that.type && Objects.equals(prizePool, that.prizePool);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventId, date, time, duration, playersPerSlot, slotCount, remainingSlots, type, prizePool);
    }

    @Override
    public String toString() {
        return "EventDetails{" +
                "id=" + id +
                ", eventId=" + eventId +
                ", date=" + date +
                ", time=" + time +
                ", duration=" + duration +
                ", playersPerSlot=" + playersPerSlot +
                ", slotCount=" + slotCount +
                ", remainingSlots=" + remainingSlots +
                ", type=" + type +
                ", prizePool=" + prizePool +
                '}';
    }
}
