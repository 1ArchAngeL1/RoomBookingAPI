package com.example.meetingroom.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "ROOM_ID")
    private Long room_id;

    @Column(name = "NUM_ALLOWED")
    private int people_allowed;

    @Column(name = "CREATOR")
    private String room_creator;

    public String getRoom_creator() {
        return room_creator;
    }

    public void setRoom_creator(String room_creator) {
        this.room_creator = room_creator;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.REMOVE)
    private List<Reservation> reservationsOnThisRoom = new ArrayList<>();


    public Room(int people_allowed, String room_creator) {
        this.people_allowed = people_allowed;
        this.room_creator = room_creator;
    }

    public Room() {
    }

    @JsonIgnore
    public List<Reservation> getReservationsOnThisRoom() {
        return reservationsOnThisRoom;
    }


    public int getPeople_allowed() {
        return people_allowed;
    }

    public void setPeople_allowed(int people_allowed) {
        this.people_allowed = people_allowed;
    }

    public Long getRoom_id() {
        return room_id;
    }
}