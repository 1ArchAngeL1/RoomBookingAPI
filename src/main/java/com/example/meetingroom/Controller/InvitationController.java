package com.example.meetingroom.Controller;

import com.example.meetingroom.DTO.CasualDto;
import com.example.meetingroom.DTO.InvitationDto;
import com.example.meetingroom.Entity.Status;
import com.example.meetingroom.Service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvitationController {

    InvitationService invitationService;

    @Autowired
    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @PostMapping(path = "/api/v1/invite")
    public void Invite(@RequestBody InvitationDto invitation) {
        invitation.setStatus(Status.PENDING);
        invitationService.addInvitation(invitation);
    }


    @PostMapping(path = "api/v1/accept")
    public void acceptInvitation(@RequestBody CasualDto id){
        invitationService.acceptInvitation(id.getId());
    }

    @PostMapping(path = "api/v1/decline")
    public void declineInvitation(@RequestBody CasualDto id){
        invitationService.acceptInvitation(id.getId());
    }
}
