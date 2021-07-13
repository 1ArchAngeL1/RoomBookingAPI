package com.example.meetingroom.Controller;

import com.example.meetingroom.DTO.*;
import com.example.meetingroom.Entity.Room;
import com.example.meetingroom.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Response> addRoom(@RequestBody RoomDto roomInfo, Principal user){
        roomInfo.setRoom_creator(user.getName());
        return new ResponseEntity<Response>(roomService.addRoom(roomInfo),HttpStatus.OK);

    }

    @DeleteMapping(path ="/api/v1/deleteroom")
    public ResponseEntity<Response> deleteRoom(@RequestBody CasualDto roomInfo,Principal user){
        return new ResponseEntity<Response>(roomService.deleteRoom(roomInfo.getId(),user.getName()),HttpStatus.OK);
    }

    @PutMapping(path = "/api/v1/changenumallowed")
    public ResponseEntity<Response> changeNumAllowed(@RequestBody ChangeNumAllowedDto roomInfo, Principal user){
        return new ResponseEntity<Response>(roomService.changeNumAllowed(roomInfo,user.getName()),HttpStatus.OK);
    }

    @PutMapping(path = "/api/v1/changeowner")
    public ResponseEntity<Response> changeOwner(@RequestBody ChangeRoomCreatorDto roomInfo, Principal user){
        return new ResponseEntity<Response>(roomService.giveHosting(roomInfo,user.getName()),HttpStatus.OK);
    }


    @PostMapping(path = "/api/v1/getroom")
    public ResponseEntity<Response> getRoom(@RequestBody CasualDto roomInfo, Principal user){
        return new ResponseEntity<Response>(roomService.getRoom(roomInfo.getId(),user.getName()), HttpStatus.OK);
    }

}
