package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.Flight;

import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
public interface FlightRepository {

    public List<Flight> findAll();

    public Flight findById(Long id);

    public Flight create(Flight flight);
}
