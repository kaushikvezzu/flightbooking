package com.example.flightbooking.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Flight
{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int flightid;

    @NotNull
    @Column(unique = true)
    private String flightcode;

    @NotNull
    private String origin;

    @NotNull
    private String destination;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name="time1")
    private LocalDateTime time;

    @NotNull
    @Column(columnDefinition = "integer CHECK (capacity > 0)")
    private int capacity;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Passenger> listpassengers;

    @PrePersist
    @PreUpdate
    private void  validate()
    {
        if (!flightcode.matches("FL\\d{4}")) {
            throw new IllegalArgumentException("Enter the Flight code in proper format");
        }

        if (origin.trim().isEmpty()||origin==null||origin.length()<2) {
            throw new IllegalArgumentException("Origin City cannot be empty");
        }

        if (destination.trim().isEmpty()||origin==null||origin.length()<2) {
            throw new IllegalArgumentException("Destination cannot be empty");
        }

        if (time.isBefore(LocalDateTime.now()) || time==null) {
            throw new IllegalArgumentException("Please enter some future time");
        }

        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
    }


    public int getflightid() {
        return flightid;
    }

    public void setflightid(int flightid) {
        this.flightid = flightid;
    }

    public String getFlightcode() {
        return flightcode;
    }

    public void setFlightcode(String flightcode) {
        this.flightcode = flightcode;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Passenger> getListpassengers() {
        return listpassengers;
    }

    public void setListpassengers(List<Passenger> listpassengers) {
        this.listpassengers = listpassengers;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightid=" + flightid +
                ", flightcode='" + flightcode + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", time=" + time +
                ", capacity=" + capacity +
                ", listpassengers=" + listpassengers +
                '}';
    }
}
