package com.realdolmen.togethair.services;


import com.realdolmen.togethair.DTO.FlightDTO;
import com.realdolmen.togethair.domain.Airport;
import com.realdolmen.togethair.domain.Flight;
import com.realdolmen.togethair.domain.Seat;
import com.realdolmen.togethair.repositories.AirportRepository;
import com.realdolmen.togethair.repositories.FlightRepository;
import com.realdolmen.togethair.repositories.SeatRepository;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ApplicationScoped
public class FlightServiceBean implements Serializable{
    @Inject
    FlightRepository repository;

    @Inject
    AirportRepository airportRepository;

    @Inject
    private SeatRepository seatRepository;

    public Seat saveSeat(Seat seat){
        return seatRepository.create(seat);
    }

    public Airport findAirportByName(String name){
        return airportRepository.getByName(name);
    }


    public Flight findById(Long id){
        return repository.findById(id);
    }

    public void Create(Flight flight ){
        repository.create(flight);
    }

}
