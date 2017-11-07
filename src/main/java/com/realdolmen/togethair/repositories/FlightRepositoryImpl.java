package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.Flight;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
public class FlightRepositoryImpl implements FlightRepository{

    private EntityManager em;

    public List<Flight> findAll(){
        return em.createQuery("select f from Flight f", Flight.class).getResultList();
    }

    public Flight findById(Long id){
        return em.find(Flight.class, id);
    }

    public Flight create(Flight flight){
        em.persist(flight);
        return flight;
    }
}
