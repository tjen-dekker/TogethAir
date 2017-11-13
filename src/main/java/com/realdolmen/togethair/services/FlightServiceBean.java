package com.realdolmen.togethair.services;


import com.realdolmen.togethair.DTO.FlightDTO;
import com.realdolmen.togethair.domain.Flight;
import com.realdolmen.togethair.repositories.FlightRepository;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ApplicationScoped
public class FlightServiceBean implements Serializable{
    @Inject
    FlightRepository repository;

    public Flight findById(Long id){
        return repository.findById(id);
    }

}
