package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.Passenger;

import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
public interface PassengerRepository {

    public List<Passenger> findAll();

    public Passenger findById(Long id);

    public Passenger create(Passenger passenger);
}
