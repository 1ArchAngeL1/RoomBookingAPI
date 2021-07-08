package com.example.meetingroom.Entity;


import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "room_id",referencedColumnName = "id")
    private Room room;

    private Date date;

    private Time start_time;

    private Time end_time;

    public Reservation(Room room, Date date, Time start_time, Time end_time) {
        this.room = room;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
    }


    public Reservation() {}

    @OneToMany(mappedBy = "reservation",fetch = FetchType.LAZY)
    private List<UsersAndReservations> reservations = new ArrayList<>();



    public Long getId() {
        return id;
    }

    public Room getRoom(){
        return room;
    }

    public List<UsersAndReservations> getReservations() {
        return reservations;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }
}
