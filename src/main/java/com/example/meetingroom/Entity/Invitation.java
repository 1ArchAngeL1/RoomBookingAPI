package com.example.meetingroom.Entity;


import javax.persistence.*;

@Entity
@Table(name = "invitations")
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STATUS")
    Status status;


    @ManyToOne
    @JoinColumn(name = "USER_ID",referencedColumnName = "USERNAME")
    private User user;

    @ManyToOne
    @JoinColumn(name = "RESERVATION_ID",referencedColumnName = "ID")
    private Reservation reservation;

    public Invitation(User user, Reservation reservation) {
        this.user = user;
        this.reservation = reservation;
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
