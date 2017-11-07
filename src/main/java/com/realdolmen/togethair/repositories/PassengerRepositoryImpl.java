package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.Passenger;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
public class PassengerRepositoryImpl implements PassengerRepository{

    private EntityManager em;

    @Override
    public List<Passenger> findAll() {
        return em.createQuery("select p from Passenger p", Passenger.class).getResultList();
    }

    @Override
    public Passenger findById(Long id) {
        return em.find(Passenger.class, id);
    }

    @Override
    public Passenger create(Passenger passenger) {
        em.persist(passenger);
        return passenger;
    }
}
