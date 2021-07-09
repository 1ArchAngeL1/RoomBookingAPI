package com.example.meetingroom.Entity;


import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID",referencedColumnName = "ROOM_ID")
    private Room room;

    @Column(name = "STARTS")
    private Date  start_time;

    @Column(name = "ENDS")
    private Date  end_time;

    @ManyToOne
    @JoinColumn(name = "HOST_ID",referencedColumnName = "USERNAME")
    private User host;

    public Reservation(Room room, Date  start_time, Date  end_time,User host) {
        this.room = room;
        this.start_time = start_time;
        this.end_time = end_time;
        this.host = host;
    }

    @OneToMany(mappedBy = "reservation",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Invitation> reservations = new ArrayList<>();


    public boolean canAdd(){
        int max_size = room.getPeople_allowed();
        int current_num = reservations.size();
        return max_size > current_num;
    }

    public List<User> invitedUsers(){
        ArrayList<User> invitedUsers = new ArrayList<>();
        for(Invitation inv :reservations){
            invitedUsers.add(inv.getUser());
        }
        return invitedUsers;
    }


    public boolean interrupts(Date  starting,Date  ending){
        if(starting.compareTo(start_time) > 0 && starting.compareTo(end_time) < 0)return true;
        if(ending.compareTo(end_time) < 0 && ending.compareTo(start_time) > 0)return true;
        if(starting.compareTo(start_time) < 0 && ending.compareTo(end_time) > 0)return true;
        return false;
    }

    public Reservation() {}

    public User getHost() {
        return host;
    }

    public Long getId() {
        return id;
    }

    public Room getRoom(){
        return room;
    }

    public List<Invitation> getReservations() {
        return reservations;
    }

    public Date getStart_time() {
        return start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }
}
