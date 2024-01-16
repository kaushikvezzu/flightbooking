package com.example.flightbooking.ServiceInterfaces;

import com.example.flightbooking.models.Flight;
import com.example.flightbooking.views.FlightPassengerViewModel;
import com.example.flightbooking.views.FlightViewModel;
//import com.example.flightbooking.views.PassengerInFlightViewModel;

import java.util.List;

public interface FlightPassengerServiceInterface
{
    public List<FlightPassengerViewModel> getAll();
    public FlightPassengerViewModel getById(int id);

    public FlightPassengerViewModel getByCode(String code);
    public List<FlightPassengerViewModel> getByOrigin(String origin);
    public List<FlightPassengerViewModel> getByDestination(String destination);
}
