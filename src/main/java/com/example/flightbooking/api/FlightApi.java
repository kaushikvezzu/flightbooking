package com.example.flightbooking.api;

import com.example.flightbooking.ServiceInterfaces.FlightServiceInterface;
import com.example.flightbooking.models.Flight;
import com.example.flightbooking.views.FlightCreateViewModel;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import com.example.flightbooking.views.FlightUpdateViewModel;
import com.example.flightbooking.views.FlightViewModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RefreshScope
@RestController
@RequestMapping("api/v1/flight")
public class FlightApi
{
    private final FlightServiceInterface flightService;

    public FlightApi(FlightServiceInterface flightServiceInterface) {
        this.flightService = flightServiceInterface;
    }

    @Operation(summary = "Add a new Flight", description = "Input: FlightCreateViewModel - Output:FlightViewModel")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<FlightViewModel> add(@RequestBody FlightCreateViewModel f)
    {
        return ResponseEntity.ok(flightService.insert(f));
    }

    @Operation(summary = "Get Details of all flights", description = "Input: NA - Output:list of FlightViewModel")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<FlightViewModel>> getAll()
    {
        List<FlightViewModel> f = flightService.retrieveAll();
        for (FlightViewModel flightViewModel : f)
        {
            flightViewModel.add(linkTo(methodOn(FlightApi.class).getById(flightViewModel.getflightid())).withRel("Explore Individually"));
            flightViewModel.add(linkTo(methodOn(PassengerApi.class).getPassengerforFlight(flightViewModel.getFlightcode())).withRel("See the list of passengers associated with flight"));
        }
        return ResponseEntity.ok(f);
    }

    @Operation(summary = "Get the details of flight based on ID" , description = "Input: Id - Output:FlightViewModel")
    @GetMapping(value = "id/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<FlightViewModel> getById(@PathVariable int id)
    {
        FlightViewModel f = flightService.retrieveById(id);
        f.add(WebMvcLinkBuilder.linkTo(methodOn(FlightApi.class).getAll()).withRel("Explore All"));
        return ResponseEntity.ok(f);
    }

    @Operation(summary = "Get the details of flight based on Code" , description = "Input: FlightCode - Output:FlightViewModels")
    @GetMapping(value = "code/{code}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<FlightViewModel> getByCode(@PathVariable String code)
    {
        FlightViewModel f = flightService.retrieveByCode(code);
        f.add(linkTo(methodOn(FlightApi.class).getAll()).withRel("Explore All"));
        return ResponseEntity.ok(f);
    }

    @Operation(summary = "Get the details of flight based on Origin City",  description = "Input: Origin - Output:list of FlightViewModels")
    @GetMapping(value = "origin/{origin}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<FlightViewModel>> getByOrigin(@PathVariable String origin)
    {
        List<FlightViewModel> f = flightService.retrieveByOrigin(origin);
        for (FlightViewModel flightViewModel : f) {
            flightViewModel.add(linkTo(methodOn(FlightApi.class).getAll()).withRel("Explore All"));
        }
        return ResponseEntity.ok(f);
    }

    @Operation(summary = "Get the details of flight based on Destination City" , description = "Input: Destination - Output:list of FlightViewModel")
    @GetMapping(value = "destination/{destination}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<FlightViewModel>> getByDestination(@PathVariable String destination)
    {
        List<FlightViewModel> f = flightService.retrieveByDestination(destination);
        for (FlightViewModel flightViewModel : f) {
            flightViewModel.add(linkTo(methodOn(FlightApi.class).getAll()).withRel("Explore All"));
        }
        return ResponseEntity.ok(f);
    }

    @Operation(summary = "Sort the flights based on the capacity" , description = "Input: NA - Output:list of FlightViewModel")
    @GetMapping(value = "sortbycapacity", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<FlightViewModel>> sortByCapacity()
    {
        return ResponseEntity.ok(flightService.sortByCapacity());
    }

    @Operation(summary = "Delete a flight based on flight code", description = "Input: Code - Output:String")
    @DeleteMapping("code/{code}")
    public ResponseEntity<?> removeflight(@PathVariable String code)
    {
        flightService.removeByCode(code);
        return ResponseEntity.ok("Successfully deleted the flight with code "+code);
    }

    @Operation(summary = "Update the details of flight", description = "Input: ID - Output:FlightViewModel")
    @PutMapping(value = "id/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<FlightViewModel> updateflight(@PathVariable int id, @RequestBody FlightUpdateViewModel f)
    {
        return ResponseEntity.ok(flightService.update(id, f));
    }

    @Operation(summary = "Delete a flight based on flight id", description = "Input: ID - Output:String")
    @DeleteMapping("id/{id}")
    public ResponseEntity<?> removeflightbyid(@PathVariable int id)
    {
        flightService.removeById(id);
        return ResponseEntity.ok("Successfully deleted the flight with id: "+id);
    }

    @Operation(summary = "Sort Flights based on Minimum Capacity", description = "Input : Capacity - Output: List of FlightViewModels")
    @GetMapping("sortbymincapacity/{capacity}")
    public ResponseEntity<List<FlightViewModel>> getFlightsByMinCapacity(@PathVariable int capacity)
    {
        return ResponseEntity.ok(flightService.sortbymincapacity(capacity));
    }
}
