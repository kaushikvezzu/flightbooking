package com.example.flightbooking.views;

import jakarta.xml.bind.annotation.XmlRootElement;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

public class FlightViewModel extends RepresentationModel<FlightViewModel>
{
    private int flightid;
    private String flightcode;
    private String origin;
    private String destination;
    private LocalDateTime time;
    private int capacity;

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

    @Override
    public String toString() {
        return "FlightViewModel{" +
                "flightid=" + flightid +
                ", flightcode='" + flightcode + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", time=" + time +
                ", capacity=" + capacity +
                '}';
    }
}
