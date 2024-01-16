package com.example.flightbooking.api;

import com.example.flightbooking.ServiceImplementation.PassengerService;
import com.example.flightbooking.views.PassengerCreateViewModel;
import com.example.flightbooking.views.PassengerUpdateViewModel;
import com.example.flightbooking.views.PassengerViewModel;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RefreshScope
@RestController
@RequestMapping("api/v1/passenger")
public class PassengerApi
{
    private final PassengerService passengerService;

    public PassengerApi(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @Operation(summary = "Add a passenger", description = "Input: PassengerCreateViewModel - Output:PassengerViewModel")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PassengerViewModel> add(@RequestBody PassengerCreateViewModel p)
    {
        return ResponseEntity.ok(passengerService.insert(p));
    }

    @Operation(summary = "Get the details of all passengers" , description = "Input: NA - Output: list of PassengerViewModel")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<PassengerViewModel>> getAll()
    {
        List<PassengerViewModel> p = passengerService.retrieveAll();
        for (PassengerViewModel passengerViewModel : p)
        {
            passengerViewModel.add(linkTo(methodOn(FlightApi.class).getById(passengerViewModel.getflightid())).withRel("Details of Flight"));
        }
        return ResponseEntity.ok(p);
    }

    @Operation(summary = "Get the details of Passenger based on ID" , description = "Input: ID - Output:PassengerViewModel")
    @GetMapping(value = "{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PassengerViewModel> getById(@PathVariable int id)
    {
        PassengerViewModel p = passengerService.retrieveById(id);
        p.add(linkTo(methodOn(FlightApi.class).getById(p.getflightid())).withRel("Details of Flight"));
        return ResponseEntity.ok(p);
    }

    @Operation(summary = "Update the details of passenger" , description = "Input: ID - Output:PassengerViewModel")
    @PutMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PassengerViewModel> updatePassenger(@PathVariable int id, @RequestBody PassengerUpdateViewModel p)
    {
        return ResponseEntity.ok(passengerService.update(id, p));
    }

    @Operation(summary = "Delete a passenger" , description = "Input: ID - Output:String")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletepassenger(@PathVariable int id)
    {
        passengerService.remove(id);
        return ResponseEntity.ok("Successfully deleted the passenger with id: "+id);
    }

    @Operation(summary = "Get the passengers associated with a flight by flight code" , description = "Input: Code - Output:PassengerViewModel")
    @GetMapping(value = "flight/{code}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<PassengerViewModel>> getPassengerforFlight(@PathVariable String code)
    {
        return ResponseEntity.ok(passengerService.retrieveForFlight(code));
    }
}
