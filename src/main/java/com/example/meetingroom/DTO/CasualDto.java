package com.example.meetingroom.DTO;

import org.springframework.web.bind.annotation.RequestBody;

public class CasualDto {
    private Long Id;

    public CasualDto(Long id) {
        Id = id;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
