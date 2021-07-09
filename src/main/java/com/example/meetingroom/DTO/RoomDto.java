package com.example.meetingroom.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

public class RoomDto {
    private int num_allowed;

    public int getNum_allowed() {
        return num_allowed;
    }

    public void setNum_allowed(int num_allowed) {
        this.num_allowed = num_allowed;
    }

    public RoomDto(int num_allowed) {
        this.num_allowed = num_allowed;
    }
}
