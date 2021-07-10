package com.example.meetingroom.Security;

import com.example.meetingroom.Entity.User;
import com.example.meetingroom.Repository.UserRepository;
import com.example.meetingroom.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JPAUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public JPAUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User acc = userRepository.getById(username);
        String login = acc.getUsername();
        String password = acc.getPassword();
        if(acc == null)throw new UsernameNotFoundException("Not Found");
        return new AccDetails(login,password);
    }
}
