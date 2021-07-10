package com.example.meetingroom;

import com.example.meetingroom.Repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@SpringBootApplication
public class MeetingRoomApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeetingRoomApplication.class, args);
    }

}
