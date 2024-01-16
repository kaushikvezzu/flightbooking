package com.example.flightbooking.ServiceInterfaces;

import com.example.flightbooking.views.PassengerCreateViewModel;
import com.example.flightbooking.views.PassengerUpdateViewModel;
import com.example.flightbooking.views.PassengerViewModel;

import java.util.List;

public interface PassengerInterface
{
    public PassengerViewModel insert(PassengerCreateViewModel p);
    public List<PassengerViewModel> retrieveAll();
    public PassengerViewModel retrieveById(int id);
    public void remove(int id);
    public PassengerViewModel update(int id, PassengerUpdateViewModel p);
    public List<PassengerViewModel> retrieveForFlight(String code);
}
