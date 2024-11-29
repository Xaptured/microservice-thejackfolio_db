/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LANTeamStatus {
    PENDING(0), APPROVED(1), REJECTED(2);

    private Integer value;

    public static LANTeamStatus fromValue(Integer value) {
        for (LANTeamStatus status : LANTeamStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }

}
