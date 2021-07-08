package com.example.meetingroom.Entity;

import org.hibernate.exception.DataException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int people_allowed;

    @Column(unique = true)
    private String room_name;

    public Room(int people_allowed, String room_name) {
        this.people_allowed = people_allowed;
        this.room_name = room_name;
    }

    public Room() {}


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "room")
    private List<Reservation> reservationsOnThisRoom = new ArrayList<>();


    public int getPeople_allowed() {
        return people_allowed;
    }

    public void setPeople_allowed(int people_allowed) {
        this.people_allowed = people_allowed;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}