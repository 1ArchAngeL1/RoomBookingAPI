package com.example.meetingroom.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

public class LoginAuth {
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
