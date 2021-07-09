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
    @Column(name = "ROOM_ID")
    private Long room_id;

    @Column(name = "NUM_ALLOWED")
    private int people_allowed;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "room",cascade = CascadeType.REMOVE)
    private List<Reservation> reservationsOnThisRoom = new ArrayList<>();


    public Room(int people_allowed) {
        this.people_allowed = people_allowed;
    }


    public List<Reservation> getReservationsOnThisRoom() {
        return reservationsOnThisRoom;
    }
    public Room() {}



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