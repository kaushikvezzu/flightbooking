package com.example.flightbooking.ServiceImplementation;

import com.example.flightbooking.ServiceInterfaces.PassengerInterface;
import com.example.flightbooking.exceptions.DataAccessException;
import com.example.flightbooking.exceptions.NoRecordFoundException;
import com.example.flightbooking.exceptions.RecordNotFoundException;
import com.example.flightbooking.models.Flight;
import com.example.flightbooking.models.Passenger;
import com.example.flightbooking.repository.PassengerRepository;
import com.example.flightbooking.views.PassengerCreateViewModel;
import com.example.flightbooking.views.PassengerUpdateViewModel;
import com.example.flightbooking.views.PassengerViewModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerService implements PassengerInterface
{
    private final FlightService flightService;
    private final PassengerRepository passengerRepository;


    public PassengerService( FlightService flightService, PassengerRepository passengerRepository) {
        this.flightService = flightService;
       // this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
    }

    @Override
    public PassengerViewModel insert(PassengerCreateViewModel p)
    {
        Flight f = flightService.getEntityByCode(p.getFlightcode());
        List<Passenger> allp = getAllforme();
        int flag = 0;
        for (Passenger passenger : allp)
        {
            if(passenger.getEmail().equals(p.getEmail())
            && passenger.getMobile().equals(p.getMobile())
            && passenger.getFlightid()==f.getflightid())
            {
                flag = 1;
                System.out.println(p.toString()+passenger.toString());
                break;
            }
        }
        if(flag ==1)
            throw new DataAccessException("The passenger already exists for the flight with code "+p.getFlightcode());
        else
        {
            Passenger p1 = toEntity(p);
            p1.setFlightid(f.getflightid());
            return toViewModel(passengerRepository.saveAndFlush(p1));
        }
    }

    @Override
    public List<PassengerViewModel> retrieveAll()
    {
        List<Passenger> p = passengerRepository.findAll();
        if(p.isEmpty())
        {
            throw new NoRecordFoundException("No Records Found Matching your query");
        }
        else
        {
            return p.stream().map(this::toViewModel).collect(Collectors.toList());
        }
    }

    @Override
    public PassengerViewModel retrieveById(int id)
    {
        try {
            return toViewModel(getEntityById(id));
        } catch (Exception e) {
            throw new DataAccessException("Please provide the request in correct format");
        }
    }

    @Override
    public void remove(int id)
    {
        Passenger p = getEntityById(id);
        if(p==null)
            throw new DataAccessException("The passenger you want to delete does not exist");
        else
        {
            passengerRepository.delete(p);
        }
    }

    @Override
    public PassengerViewModel update(int id, PassengerUpdateViewModel p)
    {
        Passenger p1 = getEntityById(id);
        BeanUtils.copyProperties(p,p1);
        if(p1==null)
            throw new DataAccessException("The passenger you want to update Does not exist");
        else
            return toViewModel(passengerRepository.saveAndFlush(p1));
    }

    @Override
    public List<PassengerViewModel> retrieveForFlight(String code)
    {
        int id = flightService.getEntityByCode(code).getflightid();
        return passengerRepository.findByFlightid(id).stream().map(this::toViewModel).collect(Collectors.toList());
    }


    private PassengerViewModel toViewModel(Passenger entity)
    {
        PassengerViewModel c = new PassengerViewModel();
        BeanUtils.copyProperties(entity, c);
        return c;
    }

    private Passenger toEntity(PassengerCreateViewModel c)
    {
        Passenger c1 = new Passenger();
        BeanUtils.copyProperties(c,c1);
        return c1;
    }

    private Passenger getEntityById(int id)
    {
        return passengerRepository.findById(id).orElseThrow(()->  new RecordNotFoundException(String.format("Passenger with id: %d is not found", id)));
    }

    private List<Passenger> getAllforme()
    {
        return passengerRepository.findAll();
    }

}
