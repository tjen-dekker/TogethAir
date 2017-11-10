package com.realdolmen.togethair.services;

import com.realdolmen.togethair.domain.Passenger;
import com.realdolmen.togethair.repositories.PassengerRepository;
import com.realdolmen.togethair.repositories.SeatRepository;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ApplicationScoped
public class PassengerServiceBean implements Serializable {

    @Inject
    PassengerRepository repository;
    @Inject
    SeatRepository seatRepository;

    public void create(Passenger p){
        repository.create(p);
        seatRepository.update(p.getSeat());
    }

}
