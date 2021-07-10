package com.example.meetingroom.DTO;

public class ChangeRoomCreatorDto {
    private Long room_id;
    private String room_creator;

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public String getRoom_creator() {
        return room_creator;
    }

    public void setRoom_creator(String room_creator) {
        this.room_creator = room_creator;
    }

    public ChangeRoomCreatorDto(Long room_id, String room_creator) {
        this.room_id = room_id;
        this.room_creator = room_creator;
    }
}
