package com.example.meetingroom.Service;

import com.example.meetingroom.DTO.ErrorMessege;
import com.example.meetingroom.DTO.InvitationDto;
import com.example.meetingroom.DTO.Response;
import com.example.meetingroom.DTO.UserDto;
import com.example.meetingroom.Entity.Invitation;
import com.example.meetingroom.Entity.User;
import com.example.meetingroom.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getInsideUser(String username) {
        if (userRepository.existsById(username)) {
            return userRepository.getById(username);
        }
        return null;
    }

    public Response getUser(String username) {
        if (userRepository.existsById(username)) {
            User user = userRepository.getById(username);
            UserDto dto = new UserDto(user.getUsername(), user.getPassword(), user.getFull_name());
            return new Response(dto);
        }
        return new Response(new ErrorMessege("dont have access to this user"));
    }

    public Response addUser(UserDto userInfo) {
        String username = userInfo.getUsername();
        String password = userInfo.getPassword();
        String full_name = userInfo.getFull_name();
        if (!userRepository.existsById(username)) {
            userRepository.save(new User(username, password, full_name));
            return new Response(userInfo);
        }
        return new Response(new ErrorMessege("username already in use"));
    }


    public Response getInvitations(String username) {
        if (userRepository.existsById(username)) {
            ArrayList<InvitationDto> invitations = new ArrayList<>();
            for (Invitation inv : userRepository.getById(username).getInvitations()) {
                invitations.add(new InvitationDto(inv.getUser().getUsername(), inv.getReservation().getId()));
            }
            return new Response(invitations);
        }
        return new Response(new ArrayList<>());
    }
}
