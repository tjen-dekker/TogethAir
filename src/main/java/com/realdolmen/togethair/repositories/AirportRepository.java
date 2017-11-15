package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.Airport;
import com.realdolmen.togethair.domain.City;

import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
public interface AirportRepository {

     List<Airport> findAll();

     Airport findById(Long id);
    
    List<Airport> findByCity(City city);

     Airport create(Airport airport);

     Airport getByName(String name);
}
