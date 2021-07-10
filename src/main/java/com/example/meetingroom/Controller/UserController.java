package com.example.meetingroom.Controller;

import com.example.meetingroom.DTO.CasualStringDto;
import com.example.meetingroom.DTO.LoginAuth;
import com.example.meetingroom.DTO.UserDto;
import com.example.meetingroom.Entity.Invitation;
import com.example.meetingroom.Entity.User;
import com.example.meetingroom.Service.UserService;
import com.example.meetingroom.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    private JwtUtil jwtUtil;

    private AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService,JwtUtil jwtUtil,AuthenticationManager authenticationManager){
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/welcome")
    public String welcome(Principal user){
        return user.getName();
    }

    @GetMapping
    @RequestMapping(path = "/api/v1/authenticate")
    public String login(@RequestBody LoginAuth user) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(),user.getPassword())
            );
        }catch (Exception ex){
            throw new Exception("username or password is invalid");
        }
        System.out.println("mevedii");
        return jwtUtil.generateToken(user.getUsername());
    }

    @PostMapping
    @RequestMapping(path = "/api/v1/register")
    public void register(@RequestBody UserDto info,Principal user){
        String password = info.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        info.setPassword(encoder.encode(password));
        userService.addUser(info);
    }

//    @DeleteMapping
//    @RequestMapping(path = "/api/v1/deleteuser")
//    public void delete(@RequestBody CasualStringDto info){
//        userService.deleteUser(info.getUsername());
//    }

    @GetMapping("/api/v1/getuser")
    public User getUser(@RequestBody CasualStringDto info,Principal user){
        return userService.getUser(info.getUsername());
    }

    @GetMapping(path = "/api/v1/getinvitations")
    @ResponseBody
    public List<Invitation> invitations(@RequestBody CasualStringDto info,Principal user){
        System.out.println(info.getUsername());
        return userService.getInvitations(info.getUsername());
    }
}
