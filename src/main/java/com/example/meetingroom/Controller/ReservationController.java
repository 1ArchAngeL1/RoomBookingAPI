package com.example.meetingroom.Controller;

import com.example.meetingroom.DTO.CasualDto;
import com.example.meetingroom.DTO.ReservationDto;
import com.example.meetingroom.Entity.Reservation;
import com.example.meetingroom.Entity.User;
import com.example.meetingroom.Repository.ReservationRepository;
import com.example.meetingroom.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class ReservationController {

    ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping(path = "/api/v1/addreservation")
    public void addReservation(@RequestBody ReservationDto info,Principal user){
        reservationService.addReservation(info,user.getName());
    }

    @DeleteMapping(path = "/api/v1/deletereservation")
    public void deleteReservation(@RequestBody  CasualDto info, Principal user){
        reservationService.cencelReservation(info.getId());
    }

    @GetMapping(path = "/api/v1/getreservation")
    public Reservation getReservation(@RequestBody CasualDto info,Principal user){
       return reservationService.getReservation(info.getId(),user.getName());
    }

    @GetMapping(path = "/api/v1/getallreservations")
    public List<Reservation> getAllReservations(Principal user){
        return reservationService.getAllReservations(user.getName());
    }


    @GetMapping(path = "/api/v1/getinvitedusers")
    public List<User> getInvitedUsers(@RequestBody CasualDto info,Principal user){
        return reservationService.getInvitedUsers(info.getId(),user.getName());
    }
}
