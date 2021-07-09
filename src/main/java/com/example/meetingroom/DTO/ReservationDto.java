package com.example.meetingroom.DTO;

import com.example.meetingroom.Entity.Room;
import com.example.meetingroom.Entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Calendar;
import java.util.Date;

public class ReservationDto {
    @JsonInclude
    private Long room_id;
    @JsonInclude
    private Date  start_time;
    @JsonInclude
    private Date  end_time;
    @JsonInclude
    private String host_name;


    public ReservationDto(Long room_id, Date start_time, Date  end_time, String host_name) {
        this.room_id = room_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.host_name = host_name;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public Date  getEnd_time() {
        return end_time;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public void setStart_time(java.sql.Time  start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(java.sql.Time  end_time) {
        this.end_time = end_time;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }
}
