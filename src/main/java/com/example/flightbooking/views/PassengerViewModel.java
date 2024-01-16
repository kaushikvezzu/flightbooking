package com.example.flightbooking.views;

import org.springframework.hateoas.RepresentationModel;

public class PassengerViewModel extends RepresentationModel<PassengerViewModel>
{
    private int passenger_id;
    private String first_name;
    private String last_name;
    private Long mobile;
    private String email;
    private int flightid;

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

    public int getflightid() {
        return flightid;
    }

    public void setflightid(int flightid) {
        this.flightid = flightid;
    }
}
