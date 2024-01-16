package com.example.flightbooking.repository;

import com.example.flightbooking.models.Flight;
import com.example.flightbooking.views.FlightViewModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Integer>
{
     public Optional<Flight> findByFlightcode(String flight_code);
     public List<Flight> findByOrigin(String origin);
     public List<Flight> findByDestination(String destination);
     public List<Flight> findAllByOrderByCapacityAsc();
     public List<Flight> findByCapacityGreaterThanOrderByCapacityDesc(int capacity);
     public void deleteByFlightcode(String code);
}
