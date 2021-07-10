package com.example.meetingroom.Controller;

import com.example.meetingroom.DTO.*;
import com.example.meetingroom.Entity.Room;
import com.example.meetingroom.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class RoomController {

    RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @PostMapping(path ="/api/v1/addroom")
    public void addRoom(@RequestBody RoomDto roomInfo, Principal user){
        roomInfo.setRoom_creator(user.getName());
        roomService.addRoom(roomInfo);
    }

    @DeleteMapping(path ="/api/v1/deleteroom")
    public void deleteRoom(@RequestBody CasualDto roomInfo,Principal user){
        roomService.deleteRoom(roomInfo.getId(),user.getName());
    }

    @PutMapping(path = "/api/v1/changenumallowed")
    public void changeNumAllowed(@RequestBody ChangeNumAllowedDto roomInfo, Principal user){
        roomService.changeNumAllowed(roomInfo,user.getName());
    }

    @PutMapping(path = "/api/v1/changeowner")
    public void changeOwner(@RequestBody ChangeRoomCreatorDto roomInfo, Principal user){
        roomService.giveHosting(roomInfo,user.getName());
    }


    @GetMapping(path = "/api/v1/getroom")
    public RoomDto getRoom(@RequestBody CasualDto roomInfo,Principal user){
        return roomService.getRoom(roomInfo.getId(),user.getName());
    }

}
