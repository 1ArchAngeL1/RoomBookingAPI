package com.example.meetingroom.Service;

import com.example.meetingroom.DTO.ReservationDto;
import com.example.meetingroom.Entity.Reservation;
import com.example.meetingroom.Entity.Room;
import com.example.meetingroom.Entity.User;
import com.example.meetingroom.Repository.ReservationRepository;
import com.example.meetingroom.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;
    private UserService userService;
    private RoomService roomService;

    @Autowired
    public ReservationService(@Lazy ReservationRepository reservationRepository,
                              @Lazy UserService userService,
                              @Lazy RoomService roomService){
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.roomService = roomService;
    }

    public void addReservation(ReservationDto reservationInfo){
        String hostname = reservationInfo.getHost_name();
        Date  starting = reservationInfo.getStart_time();
        Date  ending = reservationInfo.getEnd_time();
        Long room_id = reservationInfo.getRoom_id();
        List<Reservation> reservations = reservationRepository.findAll();
        List<Reservation> result = reservations.stream().filter(reservation ->
                reservation.interrupts(starting,ending) == true).
                collect(Collectors.toList());
        if(result.size() == 0){
            if(userService.getUser(hostname) != null && roomService.getRoom(room_id) != null){
                User host = userService.getUser(hostname);
                Room room = roomService.getRoom(room_id);
                Reservation newReservation = new Reservation(room,starting,ending,host);
                reservationRepository.save(newReservation);
            }
        }
    }

    public Reservation getReservation(Long id){
        if(reservationRepository.existsById(id))return reservationRepository.getById(id);
        return null;
    }

    public void deleteReservation(Long id){
        if(reservationRepository.existsById(id))reservationRepository.deleteById(id);
    }

}
