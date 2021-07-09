package com.example.meetingroom.Controller;

import com.example.meetingroom.DTO.CasualDto;
import com.example.meetingroom.DTO.ReservationDto;
import com.example.meetingroom.Entity.User;
import com.example.meetingroom.Repository.ReservationRepository;
import com.example.meetingroom.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {

    ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping(path = "/api/v1/addreservation")
    public void addReservation(@RequestBody ReservationDto info){
        System.out.println(info.getStart_time());
        System.out.println(info.getRoom_id());
        reservationService.addReservation(info);
    }

    @DeleteMapping(path = "/api/v1/deletereservation")
    public void deleteReservation(@RequestBody  CasualDto info){
        reservationService.cencelReservation(info.getId());
    }

    @GetMapping(path = "/api/v1/getreservation")
    public void getReservation(@RequestBody CasualDto info){
        reservationService.getReservation(info.getId());
    }


    @GetMapping(path = "/api/v1/getinvitedusers")
    public List<User> getInvitedUsers(@RequestBody CasualDto info){
        return reservationService.getInvitedUsers(info.getId());
    }
}
