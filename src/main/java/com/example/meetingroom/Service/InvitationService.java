package com.example.meetingroom.Service;

import com.example.meetingroom.DTO.ErrorMessege;
import com.example.meetingroom.DTO.InvitationDto;
import com.example.meetingroom.DTO.Response;
import com.example.meetingroom.Entity.Invitation;
import com.example.meetingroom.Entity.Reservation;
import com.example.meetingroom.Entity.Enums.Status;
import com.example.meetingroom.Entity.User;
import com.example.meetingroom.Repository.InvitationRepository;
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
    ) {
        this.invitationRepository = invitationRepository;
        this.userService = userService;
        this.reservationService = reservationService;
    }

    public Response addInvitation(InvitationDto invitation, String hostname) {
        String username = invitation.getUsername();
        if (hostname.equals(username)) return new Response(new ErrorMessege("bad credentials"));
        Long reservation_id = invitation.getReservation_id();
        Status status = invitation.getStatus();
        User user = userService.getInsideUser(username);
        Reservation reservation = reservationService.getReservationInside(reservation_id);
        if (reservation == null) return new Response(new ErrorMessege("bad credentials"));
        for (User u : reservation.invitedUsers()) {
            if (u.getUsername().equals(invitation.getUsername()))
                return new Response(new ErrorMessege("user already invited"));
        }
        if (user != null && reservation != null) {
            if (reservation.getHost().getUsername().equals(hostname)) {
                Invitation inv = new Invitation(user, reservation, status);
                if (!reservation.canAdd()) return new Response(new ErrorMessege("room if full"));
                invitationRepository.save(inv);
                invitation.setStatus(Status.PENDING);
                invitation.setInvitation_id(inv.getId());
                return new Response(inv);
            } else {
                return new Response(new ErrorMessege("access denied"));
            }
        } else {
            return new Response(new ErrorMessege("bad credentials"));
        }
    }

    public List<Invitation> getAll() {
        return invitationRepository.findAll();
    }


    public Response cencelInvitation(Long invitation_id, String username) {
        if (invitationRepository.existsById(invitation_id)) {
            Invitation inv = invitationRepository.getById(invitation_id);
            Reservation res = inv.getReservation();
            User host = res.getHost();
            if (host.getUsername().equals(username)) {
                invitationRepository.deleteById(invitation_id);
                InvitationDto invitation = new InvitationDto(inv.getUser().getUsername(), inv.getReservation().getId());
                invitation.setInvitation_id(inv.getId());
                return new Response(invitation);
            } else {
                return new Response(new ErrorMessege("access denied"));
            }
        } else {
            return new Response(new ErrorMessege("bad credentials"));
        }
    }

    public Response acceptInvitation(Long invitation_id, String username) {
        if (invitationRepository.existsById(invitation_id)) {
            Invitation inv = invitationRepository.getById(invitation_id);
            if (inv.getUser().getUsername().equals(username)) {
                inv.setStatus(Status.ACCEPTED);
                invitationRepository.save(inv);
                InvitationDto invitation = new InvitationDto(inv.getUser().getUsername(), inv.getReservation().getId());
                invitation.setInvitation_id(inv.getId());
                invitation.setStatus(Status.ACCEPTED);
                return new Response(invitation);
            } else {
                return new Response(new ErrorMessege("access denied"));
            }
        } else {
            return new Response(new ErrorMessege("bad credentials"));
        }
    }

    public Response rejectInvitation(Long invitation_id, String username) {
        if (invitationRepository.existsById(invitation_id)) {
            Invitation inv = invitationRepository.getById(invitation_id);
            if (inv.getUser().getUsername().equals(username)) {
                inv.setStatus(Status.REJECTED);
                invitationRepository.save(inv);
                InvitationDto invitation = new InvitationDto(inv.getUser().getUsername(), inv.getReservation().getId());
                invitation.setInvitation_id(inv.getId());
                invitation.setStatus(Status.REJECTED);
                return new Response(invitation);
            } else {
                return new Response(new ErrorMessege("access denied"));
            }
        } else {
            return new Response(new ErrorMessege("bad credentials"));
        }
    }

    public Invitation getInvitation(Long invitation_id) {
        if (invitationRepository.existsById(invitation_id))
            return invitationRepository.getById(invitation_id);
        return null;
    }

}
