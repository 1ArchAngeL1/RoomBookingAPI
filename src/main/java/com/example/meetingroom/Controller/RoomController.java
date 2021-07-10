package com.example.meetingroom.Controller;

import com.example.meetingroom.DTO.CasualDto;
import com.example.meetingroom.DTO.EditRoomDto;
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

    @PostMapping(path ="/api/v1/addroom")
    public void addRoom(@RequestBody RoomDto roomInfo){
        roomService.addRoom(roomInfo);
    }

    @DeleteMapping(path ="/api/v1/deleteroom")
    public void deleteRoom(@RequestBody CasualDto roomInfo){
        roomService.deleteRoom(roomInfo.getId());
    }

    @PutMapping(path = "/api/v1/editroom")
    public void editRoom(@RequestBody EditRoomDto roomInfo){
        roomService.editRoom(roomInfo);
    }

    @GetMapping(path = "/api/v1/getroom")
    public Room getRoom(@RequestBody CasualDto roomInfo){
        return roomService.getRoom(roomInfo.getId());
    }

}
