package com.example.meetingroom.Entity;


import com.example.meetingroom.Entity.Enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "invitations")
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    Status status;

    @ManyToOne
    @JoinColumn(name = "USER_ID",referencedColumnName = "USERNAME")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "RESERVATION_ID",referencedColumnName = "ID")
    private Reservation reservation;

    public Invitation(User user, Reservation reservation,Status status) {
        this.user = user;
        this.reservation = reservation;
        this.status = status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Invitation() {}

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Reservation getReservation() {
        return reservation;
    }

}
