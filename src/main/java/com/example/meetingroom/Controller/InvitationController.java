package com.example.meetingroom.Controller;

import com.example.meetingroom.DTO.CasualDto;
import com.example.meetingroom.DTO.InvitationDto;
import com.example.meetingroom.DTO.Response;
import com.example.meetingroom.Entity.Enums.Status;
import com.example.meetingroom.Service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class InvitationController {
    InvitationService invitationService;

    @Autowired
    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @PostMapping(path = "/api/v1/invite")
    public ResponseEntity<Response> Invite(@RequestBody InvitationDto invitation, Principal user) {
        invitation.setStatus(Status.PENDING);
        return new ResponseEntity<Response>(invitationService.addInvitation(invitation, user.getName()), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/cencelinvitation")
    public ResponseEntity<Response> cencelInvitation(@RequestBody CasualDto info, Principal user) {
        return new ResponseEntity<Response>(invitationService.cencelInvitation(info.getId(), user.getName()), HttpStatus.OK);
    }

    @PutMapping(path = "api/v1/accept")
    public ResponseEntity<Response> acceptInvitation(@RequestBody CasualDto id, Principal user) {
        return new ResponseEntity<Response>(invitationService.acceptInvitation(id.getId(), user.getName()), HttpStatus.OK);
    }

    @DeleteMapping(path = "api/v1/decline")
    public ResponseEntity<Response> declineInvitation(@RequestBody CasualDto id, Principal user) {
        return new ResponseEntity<Response>(invitationService.rejectInvitation(id.getId(), user.getName()), HttpStatus.OK);
    }
}
