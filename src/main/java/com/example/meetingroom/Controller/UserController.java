package com.example.meetingroom.Controller;

import com.example.meetingroom.DTO.CasualStringDto;
import com.example.meetingroom.DTO.UserDto;
import com.example.meetingroom.Entity.Invitation;
import com.example.meetingroom.Entity.User;
import com.example.meetingroom.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    @RequestMapping(path = "/api/v1/register")
    public void register(@RequestBody UserDto info){
        userService.addUser(info);
    }

    @PostMapping
    @RequestMapping(path = "/api/v1/deleteuser")
    public void delete(@RequestBody UserDto info){
        userService.deleteUser(info);
    }
    @GetMapping("/api/v1/getuser")
    public User getUser(@RequestBody UserDto info){
        return userService.getUser(info.getUsername());
    }

    @GetMapping(path = "/api/v1/getinvitations")
    @ResponseBody
    public List<Invitation> invitations(@RequestBody CasualStringDto user){
        System.out.println(user.getUsername());
        return userService.getInvitations(user.getUsername());
    }
}
