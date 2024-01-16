package com.example.flightbooking.views;

import com.example.flightbooking.models.Passenger;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightPassengerViewModel extends RepresentationModel<FlightPassengerViewModel>
{
    private int flightid;
    private String flightcode;
    private String origin;
    private String destination;
    private LocalDateTime time;
    private int capacity;
    private List<Passenger> listpassengers;

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

    public int getflightid() {
        return flightid;
    }

    public void setflightid(int flightid) {
        this.flightid = flightid;
    }


}
