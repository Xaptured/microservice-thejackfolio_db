/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import com.thejackfolio.microservices.thejackfolio_db.enums.LANEventStatus;
import com.thejackfolio.microservices.thejackfolio_db.enums.LANEventType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "lan_events")
public class LANEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "game_name")
    private String gameName;
    @Column(name = "address_one")
    private String addressLineOne;
    @Column(name = "address_two")
    private String addressLineTwo;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "zip")
    private String zipCode;
    @Column(name = "event_type")
    private LANEventType eventType;
    @Column(name = "prize_pool")
    private BigDecimal prizePool;
    @Column(name = "total_slots")
    private Integer totalSlots;
    @Column(name = "date")
    private String date;
    @Column(name = "event_status")
    private LANEventStatus status;
    @Column(name = "start_check_in_process")
    private boolean startCheckInProcess;
}
