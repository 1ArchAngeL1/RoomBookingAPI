package com.example.meetingroom.DTO;

import com.example.meetingroom.Entity.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class InvitationDto {
    @JsonIgnore
    Status status;

    @JsonInclude
    String username;

    @JsonInclude
    Long reservation_id;

    public InvitationDto(String username, Long reservation_id) {
        this.username = username;
        this.reservation_id = reservation_id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(Long reservation_id) {
        this.reservation_id = reservation_id;
    }
}
