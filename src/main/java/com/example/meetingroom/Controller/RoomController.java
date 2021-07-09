package com.example.meetingroom.Controller;

import com.example.meetingroom.DTO.CasualDto;
import com.example.meetingroom.DTO.EditRoom;
import com.example.meetingroom.DTO.RoomDto;
import com.example.meetingroom.Entity.Room;
import com.example.meetingroom.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoomController {

    RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @PostMapping("/api/v1/addrooms")
    public void addRoom(@RequestBody RoomDto roomInfo){
        roomService.addRoom(roomInfo);
    }

    @DeleteMapping("/api/v1/deleterooms")
    public void deleteRoom(@RequestBody CasualDto roomInfo){
        roomService.deleteRoom(roomInfo.getId());
    }

    @PutMapping("/api/v1/editrooms")
    public void editRoom(@RequestBody EditRoom roomInfo){
        roomService.editRoom(roomInfo);
    }

    @GetMapping("/api/v1/getrooms")
    public Room getRoom(@RequestBody CasualDto roomInfo){
        return roomService.getRoom(roomInfo.getId());
    }

}
