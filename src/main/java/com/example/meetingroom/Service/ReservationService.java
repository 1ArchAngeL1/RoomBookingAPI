package com.example.meetingroom.Service;

import com.example.meetingroom.DTO.*;
import com.example.meetingroom.Entity.Invitation;
import com.example.meetingroom.Entity.Reservation;
import com.example.meetingroom.Entity.Room;
import com.example.meetingroom.Entity.User;
import com.example.meetingroom.Repository.ReservationRepository;
import com.example.meetingroom.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;
    private UserService userService;
    private RoomService roomService;
    private InvitationService invitationService;

    @Autowired
    public ReservationService(@Lazy ReservationRepository reservationRepository,
                              @Lazy UserService userService,
                              @Lazy RoomService roomService,
                              @Lazy InvitationService invitationService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.roomService = roomService;
        this.invitationService = invitationService;
    }


    public Response addReservation(ReservationDto reservationInfo, String username) {
        String hostname = username;
        Date starting = reservationInfo.getStart_time();
        Date ending = reservationInfo.getEnd_time();
        Long room_id = reservationInfo.getRoom_id();
        List<Reservation> reservations = reservationRepository.findAll();
        List<Reservation> result = reservations.stream().filter(reservation ->
                        reservation.interrupts(starting, ending) == true).
                collect(Collectors.toList());
        if (result.size() == 0) {
            if (roomService.getRoomInside(room_id) != null) {
                User host = userService.getInsideUser(hostname);
                Room room = roomService.getRoomInside(room_id);
                if (!room.getRoom_creator().equals(username))
                    return new Response(new ErrorMessege("you dont have access on the room"));
                Reservation newReservation = new Reservation(room, starting, ending, host);
                reservationRepository.save(newReservation);
                reservationInfo.setReservation_id(newReservation.getId());
                reservationInfo.setHost_name(newReservation.getHost().getUsername());
                return new Response(reservationInfo);
            } else {
                return new Response(new ErrorMessege("room with given id doesn't exist"));
            }
        } else {
            return new Response(new ErrorMessege("room is already booked at the given time"));
        }
    }

    public Response getReservation(Long id, String username) {
        if (reservationRepository.existsById(id)) {
            Reservation res = reservationRepository.getById(id);
            if (res.getHost().getUsername().equals(username)) {
                ReservationDto dto = new ReservationDto(res.getRoom().getRoom_id(), res.getStart_time(), res.getEnd_time());
                dto.setReservation_id(res.getId());
                dto.setHost_name(res.getHost().getUsername());
                return new Response(dto);
            } else {
                return new Response(new ErrorMessege("access denied"));
            }
        } else {
            return new Response(new ErrorMessege("reservation with given id doesn't exist"));
        }
    }

    public Response getAllReservations(String username) {
        if (userService.getUser(username) != null) {
            List<ReservationDto> dtos = new ArrayList<>();
            List<Reservation> reservations = userService.getInsideUser(username).getHostedMeetings();
            for (Reservation res : reservations) {
                ReservationDto dto = new ReservationDto(res.getRoom().getRoom_id(), res.getStart_time(), res.getEnd_time());
                dto.setReservation_id(res.getId());
                dto.setHost_name(res.getHost().getUsername());
                dtos.add(dto);
            }
            return new Response(dtos);
        }
        return new Response(new ErrorMessege("user doesn't exist"));
    }


    public Response cencelReservation(Long id, String username) {
        if (reservationRepository.existsById(id)) {
            Reservation res = reservationRepository.getById(id);
            if (res.getHost().getUsername().equals(username)) {
                reservationRepository.deleteById(id);
                ReservationDto dto = new ReservationDto(res.getRoom().getRoom_id(), res.getStart_time(), res.getEnd_time());
                dto.setReservation_id(res.getId());
                dto.setHost_name(res.getHost().getUsername());
                return new Response(dto);
            } else {
                return new Response(new ErrorMessege("access denied"));
            }
        } else {
            return new Response(new ErrorMessege("reservation doesn't exist"));
        }
    }

    public Response getInvitedUsers(Long id, String username) {
        if (reservationRepository.existsById(id)) {
            Reservation res = reservationRepository.getById(id);
            if (res.getHost().getUsername().equals(username)) {
                List<User> users = res.invitedUsers();
                List<UserDto> dtos = new ArrayList<>();
                for (User user : users) {
                    dtos.add(new UserDto(user.getUsername(), "******", user.getFull_name()));
                }
                return new Response(dtos);
            } else {
                return new Response(new ErrorMessege("access denied"));
            }
        } else {
            return new Response(new ErrorMessege("reservation doesn't exist"));
        }
    }

    public Reservation getReservationInside(Long id) {
        if (reservationRepository.existsById(id)) return reservationRepository.getById(id);
        return null;
    }
}
