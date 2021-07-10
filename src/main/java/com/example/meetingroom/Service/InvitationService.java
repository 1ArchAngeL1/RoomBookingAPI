package com.example.meetingroom.Service;

import com.example.meetingroom.DTO.CasualDto;
import com.example.meetingroom.DTO.InvitationDto;
import com.example.meetingroom.Entity.Invitation;
import com.example.meetingroom.Entity.Reservation;
import com.example.meetingroom.Entity.Status;
import com.example.meetingroom.Entity.User;
import com.example.meetingroom.Repository.InvitationRepository;
import com.example.meetingroom.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationService {

    InvitationRepository invitationRepository;
    UserService userService;
    ReservationService reservationService;

    @Autowired
    public InvitationService(InvitationRepository invitationRepository,
                             UserService userService,
                             ReservationService reservationService
    ){
        this.invitationRepository = invitationRepository;
        this.userService = userService;
        this.reservationService = reservationService;
    }

    public void addInvitation(InvitationDto invitation){
        String username = invitation.getUsername();
        Long reservation_id = invitation.getReservation_id();
        Status status = invitation.getStatus();
        User user = userService.getUser(username);
        Reservation reservation = reservationService.getReservationInside(reservation_id);
        if(user != null && reservation != null){
            invitationRepository.save(new Invitation(user,reservation,status));
        }
    }

    public List<Invitation> getAll(){
        return invitationRepository.findAll();
    }


    public void cencelInvitation(Long invitation_id){
        if(invitationRepository.existsById(invitation_id)){
            invitationRepository.deleteById(invitation_id);
        }
    }

    public void acceptInvitation(Long invitation_id){
        if(invitationRepository.existsById(invitation_id)){
            Invitation invitation = invitationRepository.getById(invitation_id);
            invitation.setStatus(Status.ACCEPTED);
            invitationRepository.save(invitation);
        }
    }
    public void rejectInvitation(Long invitation_id){
        if(invitationRepository.existsById(invitation_id)){
           invitationRepository.deleteById(invitation_id);
        }
    }

    public Invitation getInvitation(Long invitation_id){
        if(invitationRepository.existsById(invitation_id))
            return invitationRepository.getById(invitation_id);
        return null;
    }

}
