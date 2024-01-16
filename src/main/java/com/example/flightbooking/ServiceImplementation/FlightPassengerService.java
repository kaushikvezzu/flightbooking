package com.example.flightbooking.ServiceImplementation;

import com.example.flightbooking.ServiceInterfaces.FlightPassengerServiceInterface;
import com.example.flightbooking.models.Flight;
import com.example.flightbooking.repository.FlightRepository;
import com.example.flightbooking.views.FlightPassengerViewModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightPassengerService implements FlightPassengerServiceInterface
{
    private final FlightService flightService;
    private final PassengerService passengerService;
    private final FlightRepository flightRepository;

    public FlightPassengerService(FlightService flightService, PassengerService passengerService, FlightRepository flightRepository) {
        this.flightService = flightService;
        this.passengerService = passengerService;
        this.flightRepository = flightRepository;
    }

    @Override
    public List<FlightPassengerViewModel> getAll()
    {
        return flightRepository.findAll().stream().map(this::toViewModel).collect(Collectors.toList());
    }

    @Override
    public FlightPassengerViewModel getById(int id) {
        Flight f = flightService.getEntityById(id);
        return toViewModel(f);
    }

    @Override
    public FlightPassengerViewModel getByCode(String code) {
        Flight f = flightService.getEntityByCode(code);
        return toViewModel(f);
    }

    @Override
    public List<FlightPassengerViewModel> getByOrigin(String origin)
    {
        return flightService.getByOrigin(origin).stream().map(this::toViewModel).collect(Collectors.toList());
    }

    @Override
    public List<FlightPassengerViewModel> getByDestination(String destination)
    {
        return flightService.getByDestination(destination).stream().map(this::toViewModel).collect(Collectors.toList());
    }


    private FlightPassengerViewModel toViewModel(Flight flight)
    {
        FlightPassengerViewModel flightPassengerViewModel=new FlightPassengerViewModel();
        BeanUtils.copyProperties(flight,flightPassengerViewModel);
        return flightPassengerViewModel;
    }
}
