package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.Airport;

import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
public interface AirportRepository {

    public List<Airport> findAll();

    public Airport findById(Long id);

    public Airport create(Airport airport);
}
