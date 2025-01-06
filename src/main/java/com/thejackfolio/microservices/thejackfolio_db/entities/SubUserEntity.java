/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sub_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "event_name")
    private String eventName;
    @Column(name = "email_sent")
    private boolean isEmailSent;
    @Column(name = "active")
    private boolean isActive;
}
