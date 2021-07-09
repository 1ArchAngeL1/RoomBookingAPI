package com.example.meetingroom.DTO;

public class EditRoomDto {
    private int num_allowed;
    private Long room_id;

    public int getNum_allowed() {
        return num_allowed;
    }

    public void setNum_allowed(int num_allowed) {
        this.num_allowed = num_allowed;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public EditRoomDto(int num_allowed, Long room_id) {
        this.num_allowed = num_allowed;
        this.room_id = room_id;
    }
}
