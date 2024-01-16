package com.example.flightbooking.ServiceInterfaces;

import com.example.flightbooking.models.Flight;
import com.example.flightbooking.views.FlightCreateViewModel;
import com.example.flightbooking.views.FlightUpdateViewModel;
import com.example.flightbooking.views.FlightViewModel;

import java.util.List;

public interface FlightServiceInterface
{
    public FlightViewModel insert(FlightCreateViewModel f);
    public List<FlightViewModel> retrieveAll();
    public FlightViewModel retrieveById(int id);
    public FlightViewModel retrieveByCode(String code);
    public List<FlightViewModel> retrieveByOrigin(String origin);
    public List<FlightViewModel> retrieveByDestination(String destination);
    public List<FlightViewModel> sortByCapacity();
    public List<FlightViewModel> sortbymincapacity(int capacity);

    public void removeByCode(String code);
    public FlightViewModel update(int id, FlightUpdateViewModel f);
    public void removeById(int id);

}
