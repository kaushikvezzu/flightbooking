package com.example.flightbooking.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Passenger
{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int passenger_id;
    private String first_name;
    private String last_name;

    @NotNull
    private Long mobile;

    @NotNull
    private String email;
    private int flightid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flightid", insertable = false, updatable = false)
    @JsonBackReference
    private Flight flight;

    @PrePersist
    @PreUpdate
    private void validate()
    {
        if (first_name == null || first_name.trim().isEmpty()) {
            throw new IllegalArgumentException("First name is required.");
        }
        if (last_name == null || last_name.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name is required.");
        }
        if (mobile == null || String.valueOf(mobile).length()!=10) {
            throw new IllegalArgumentException("Mobile number is required with 10 digits.");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Valid email is required.");
        }
    }

    public int getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFlightid() {
        return flightid;
    }

    public void setFlightid(int flightid) {
        this.flightid = flightid;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
