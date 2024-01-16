package com.example.flightbooking.repository;

import com.example.flightbooking.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Integer>
{
    public List<Passenger> findByFlightid(int id);
}

