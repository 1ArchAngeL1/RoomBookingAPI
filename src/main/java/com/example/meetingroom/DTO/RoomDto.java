package com.example.meetingroom.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

public class RoomDto {
    private int num_allowed;
    private String room_name;


    public RoomDto(int num_allowed, String room_name) {
        this.num_allowed = num_allowed;
        this.room_name = room_name;
    }

    public String getRoom_name() {

        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getNum_allowed() {
        return num_allowed;
    }

    public void setNum_allowed(int num_allowed) {
        this.num_allowed = num_allowed;
    }
}
