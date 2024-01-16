package com.example.flightbooking.views;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

public class FlightUpdateViewModel extends RepresentationModel<FlightUpdateViewModel>
{
    private String origin;
    private String destination;
    private LocalDateTime time;

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


}
