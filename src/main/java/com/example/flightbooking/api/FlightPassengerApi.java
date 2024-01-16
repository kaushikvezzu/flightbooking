package com.example.flightbooking.api;

import com.example.flightbooking.ServiceImplementation.FlightPassengerService;
import com.example.flightbooking.views.FlightPassengerViewModel;
import com.example.flightbooking.views.FlightViewModel;
//import com.example.flightbooking.views.PassengerInFlightViewModel;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping("api/v1/flightpassenger")
public class FlightPassengerApi
{
    private final FlightPassengerService flightPassengerService;

    public FlightPassengerApi(FlightPassengerService flightPassengerService) {
        this.flightPassengerService = flightPassengerService;
    }

    @Operation(summary = "All Flights wih associated passengers", description = "Input:NA - Output:FlightPassengerViewModel")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<FlightPassengerViewModel>> getAllFlightPassenger()
    {
        return ResponseEntity.ok(flightPassengerService.getAll());
    }

    @Operation(summary = "Flights wih associated passengers by flight id", description = "Input:ID - Output:FlightPassengerViewModel")
    @GetMapping(value = "id/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<FlightPassengerViewModel> getFlightPassengerById(@PathVariable int id)
    {
        return ResponseEntity.ok(flightPassengerService.getById(id));
    }

    @Operation(summary = "Flights wih associated passengers by flight code", description = "Input:code - Output:FlightPassengerViewModel")
    @GetMapping(value = "code/{code}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<FlightPassengerViewModel> getFlightPassengerByCode(@PathVariable String code)
    {
        return ResponseEntity.ok(flightPassengerService.getByCode(code));
    }

    @Operation(summary = "Flights wih associated passengers bu origin city", description = "Input:Origin City - Output:FlightPassengerViewModel")
    @GetMapping(value = "origin/{origin}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<FlightPassengerViewModel>> getFlightPassengerByOrigin(@PathVariable String origin)
    {
        return ResponseEntity.ok(flightPassengerService.getByOrigin(origin));
    }

    @Operation(summary = "Flights wih associated passengers by Destination city", description = "Input:Destination city - Output:FlightPassengerViewModel")
    @GetMapping(value = "destination/{destination}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<FlightPassengerViewModel>> getFlightPassengerByDestination(@PathVariable String destination)
    {
        return ResponseEntity.ok(flightPassengerService.getByDestination(destination));
    }



}
