package com.example.meetingroom.Controller;

import com.example.meetingroom.DTO.CasualDto;
import com.example.meetingroom.DTO.ReservationDto;
import com.example.meetingroom.DTO.Response;
import com.example.meetingroom.Entity.Reservation;
import com.example.meetingroom.Entity.User;
import com.example.meetingroom.Repository.ReservationRepository;
import com.example.meetingroom.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Response> addReservation(@RequestBody ReservationDto info, Principal user){
        return new ResponseEntity<Response>( reservationService.addReservation(info,user.getName()),HttpStatus.OK);
    }

    @DeleteMapping(path = "/api/v1/deletereservation")
    public ResponseEntity<Response> deleteReservation(@RequestBody  CasualDto info, Principal user){
        return new ResponseEntity<Response>(reservationService.cencelReservation(info.getId(),user.getName()),HttpStatus.OK);
    }

    @PostMapping(path = "/api/v1/getreservation")
    public ResponseEntity<Response> getReservation(@RequestBody CasualDto info,Principal user){
       return new ResponseEntity<Response>(reservationService.getReservation(info.getId(),user.getName()),HttpStatus.OK);
    }

    //returns reservations hosted by user
    @GetMapping(path = "/api/v1/getallreservations")
    public ResponseEntity<Response> getAllReservations(Principal user){
        return new ResponseEntity<Response>(reservationService.getAllReservations(user.getName()),HttpStatus.OK);
    }

    //returns users invited on the reservation
    @PostMapping(path = "/api/v1/getinvitedusers")
    public ResponseEntity<Response> geInvitedUsers(@RequestBody CasualDto info, Principal user){
        return new ResponseEntity<Response>(reservationService.getInvitedUsers(info.getId(),user.getName()), HttpStatus.OK);
    }
}
