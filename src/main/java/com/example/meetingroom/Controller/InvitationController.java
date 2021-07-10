package com.example.meetingroom.Controller;

import com.example.meetingroom.DTO.CasualDto;
import com.example.meetingroom.DTO.InvitationDto;
import com.example.meetingroom.Entity.Status;
import com.example.meetingroom.Service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class InvitationController {
    InvitationService invitationService;

    @Autowired
    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    //temporary
    @GetMapping(path = "/")
    public String home(Principal principal){
        return principal.getName();
    }

    @PostMapping(path = "/api/v1/invite")
    public void Invite(@RequestBody InvitationDto invitation) {
        invitation.setStatus(Status.PENDING);
        invitationService.addInvitation(invitation);
    }

    @DeleteMapping("/api/v1/cencelinvitation")
    public void cencelInvitation(@RequestBody CasualDto info){
        invitationService.cencelInvitation(info.getId());
    }

    @PostMapping(path = "api/v1/accept")
    public void acceptInvitation(@RequestBody CasualDto id){
        invitationService.acceptInvitation(id.getId());
    }

    @DeleteMapping(path = "api/v1/decline")
    public void declineInvitation(@RequestBody CasualDto id){
        invitationService.rejectInvitation(id.getId());
    }
}
