package com.example.meetingroom.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

public class RoomDto {
    private int num_allowed;

    private Long room_id;

    private String room_creator;

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public RoomDto(int num_allowed) {
        this.num_allowed = num_allowed;
        this.room_creator = room_creator;
    }

    public int getNum_allowed() {
        return num_allowed;
    }

    public void setNum_allowed(int num_allowed) {
        this.num_allowed = num_allowed;
    }

    public String getRoom_creator() {
        return room_creator;
    }

    public void setRoom_creator(String room_creator) {
        this.room_creator = room_creator;
    }
}
