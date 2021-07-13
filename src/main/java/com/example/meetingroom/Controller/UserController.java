package com.example.meetingroom.Controller;

import com.example.meetingroom.DTO.CasualStringDto;
import com.example.meetingroom.DTO.LoginAuth;
import com.example.meetingroom.DTO.Response;
import com.example.meetingroom.DTO.UserDto;
import com.example.meetingroom.Entity.Invitation;
import com.example.meetingroom.Entity.User;
import com.example.meetingroom.Service.UserService;
import com.example.meetingroom.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @PostMapping(path = "/api/v1/authenticate")
    public ResponseEntity<Response> login(@RequestBody LoginAuth user) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(),user.getPassword())
            );
        }catch (Exception ex){
           return new ResponseEntity<>(new Response( "username or password is invalid"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new Response(jwtUtil.generateToken(user.getUsername())),HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping(path = "/api/v1/register")
    public ResponseEntity<Response> register(@RequestBody UserDto info, Principal user){
        String password = info.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        info.setPassword(encoder.encode(password));
        return new ResponseEntity(userService.addUser(info), HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/getuser")
    public ResponseEntity<Response> getUser(Principal user){
        return new ResponseEntity<>(userService.getUser(user.getName()),HttpStatus.OK);
    }

    //returns list of invitations that were sent to user
    @GetMapping(path = "/api/v1/getinvitations")
    @ResponseBody
    public ResponseEntity<Response> invitations(Principal user){
        return new ResponseEntity<>(userService.getInvitations(user.getName()),HttpStatus.OK);
    }
}
