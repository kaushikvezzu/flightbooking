package com.example.flightbooking.ServiceImplementation;

import com.example.flightbooking.ServiceInterfaces.FlightServiceInterface;
import com.example.flightbooking.exceptions.DataAccessException;
import com.example.flightbooking.exceptions.NoRecordFoundException;
import com.example.flightbooking.exceptions.RecordNotFoundException;
import com.example.flightbooking.models.Flight;
import com.example.flightbooking.repository.FlightRepository;
import com.example.flightbooking.views.FlightCreateViewModel;
import com.example.flightbooking.views.FlightUpdateViewModel;
import com.example.flightbooking.views.FlightViewModel;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService implements FlightServiceInterface
{
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public FlightViewModel insert(FlightCreateViewModel f)
    {
        try
        {
            Flight f1 = new Flight();
            BeanUtils.copyProperties(f, f1);
            Flight f3 = flightRepository.saveAndFlush(f1);
            return toViewModel(f3);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DataAccessException("the record might already exist");
        }
        catch (NullPointerException e)
        {
            throw new DataAccessException("You might have missed a attribute or you might have entered null");
        }
        catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    @Override
    public List<FlightViewModel> retrieveAll()
    {
        List<Flight> f1 =  flightRepository.findAll();
        if(f1.isEmpty())
        {
            throw new NoRecordFoundException("No Records Found Matching your query");
        }
        else
            return f1.stream().map(this::toViewModel).collect(Collectors.toList());
    }


    @Override
    public FlightViewModel retrieveById(int id)
    {
        return toViewModel(getEntityById(id));
    }

    @Override
    public FlightViewModel retrieveByCode(String code)
    {
        return toViewModel(getEntityByCode(code));
    }

    @Override
    public List<FlightViewModel> retrieveByOrigin(String origin)
    {
        return getByOrigin(origin).stream().map(this::toViewModel).collect(Collectors.toList());
    }

    @Override
    public List<FlightViewModel> retrieveByDestination(String destination)
    {
        return getByDestination(destination).stream().map(this::toViewModel).collect(Collectors.toList());
    }

    @Override
    public List<FlightViewModel> sortByCapacity()
    {
        return getBySortedCapacity().stream().map(this::toViewModel).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void removeByCode(String code)
    {
        try {
            flightRepository.deleteByFlightcode(code);
        }
        catch (Exception e)
        {
            throw new RecordNotFoundException("The Flight you wanted to delete does not exist"+ e.getMessage());
        }
    }

    @Override
    public FlightViewModel update(int id, FlightUpdateViewModel f) {
        Flight f1 = getEntityById(id);
        BeanUtils.copyProperties(f, f1);
        try {
            return toViewModel(flightRepository.saveAndFlush(f1));
        }
        catch (EntityNotFoundException e)
        {
            throw new DataAccessException("Entity with the given key already exists");
        }
        catch (Exception e)
        {
            throw new DataAccessException("Provide Request In correct Format");
        }
    }

    @Override
    public void removeById(int id)
    {
        Flight f1 = getEntityById(id);
        flightRepository.delete(f1);
    }

    @Override
    public List<FlightViewModel> sortbymincapacity(int capacity)
    {
        return flightRepository.findByCapacityGreaterThanOrderByCapacityDesc(capacity).stream().map(this::toViewModel).collect(Collectors.toList());
    }

    private FlightViewModel toViewModel(Flight entity)
    {
        FlightViewModel c = new FlightViewModel();
        BeanUtils.copyProperties(entity, c);
        return c;
    }

    private Flight toEntity(FlightCreateViewModel c)
    {
        Flight c1 = new Flight();
        BeanUtils.copyProperties(c,c1);
        return c1;
    }

    public Flight getEntityById(int id)
    {
        return flightRepository.findById(id).orElseThrow(()->  new RecordNotFoundException(String.format("Flight with id: %d is not found", id)));
    }

    public Flight getEntityByCode(String code)
    {
        return flightRepository.findByFlightcode(code).orElseThrow(()->  new RecordNotFoundException(String.format("Flight with code: %s is not found", code)));
    }

    public List<Flight> getByOrigin(String origin)
    {
        List<Flight> f = flightRepository.findByOrigin(origin);
        if(f.isEmpty())
        {
            throw new NoRecordFoundException("No records Founds. Please check the origin city");
        }
        else {
            return f;
        }
    }

    public List<Flight> getByDestination(String destination)
    {
        List<Flight> f = flightRepository.findByDestination(destination);
        if(f.isEmpty())
        {
            throw new NoRecordFoundException("No records Founds. Please check the destination city");
        }
        else {
            return f;
        }
    }

    private List<Flight> getBySortedCapacity()
    {
        List<Flight> f = flightRepository.findAllByOrderByCapacityAsc();
        if(f.isEmpty())
        {
            throw new NoRecordFoundException("No records Founds");
        }
        else {
            return f;
        }
    }
}
