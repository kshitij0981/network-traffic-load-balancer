package com.cn.backend_server;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Seat {

    @Id
    private String id;

    private String event;
    private String seatNumber;
    private boolean booked;

    public Seat() {
    }

    public Seat(String event, String seatNumber) {
        this.event = event;
        this.seatNumber = seatNumber;
        this.id = event + "_" + seatNumber;
        this.booked = false;
    }

    public String getId() {
        return id;
    }

    public String getEvent() {
        return event;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isBooked() {
        return booked;
    }
}
