package com.example.meetingroom.Entity;


import javax.persistence.*;

@Entity
@Table(name = "usersandreservations")
public class UsersAndReservations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "username")
    private User user;

    @ManyToOne
    @JoinColumn(name = "reservation_id",referencedColumnName = "id")
    private Reservation reservation;

    public UsersAndReservations(User user, Reservation reservation) {
        this.user = user;
        this.reservation = reservation;
    }

    public UsersAndReservations() {}

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
