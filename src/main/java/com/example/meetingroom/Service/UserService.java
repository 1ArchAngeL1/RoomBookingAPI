package com.example.meetingroom.Service;

import com.example.meetingroom.DTO.UserDto;
import com.example.meetingroom.Entity.User;
import com.example.meetingroom.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUser(String username){
        if(userRepository.existsById(username))return userRepository.getById(username);
        return null;
    }

    public void addUser(UserDto userInfo){
        String username = userInfo.getUsername();
        String password = userInfo.getPassword();
        String full_name = userInfo.getFull_name();
        if(!userRepository.existsById(username)){
            userRepository.save(new User(username,password,full_name));
        }
    }

    public void deleteUser(UserDto userInfo){
        String username = userInfo.getUsername();
        if(userRepository.existsById(username)){
            userRepository.deleteById(username);
        }
    }


}
