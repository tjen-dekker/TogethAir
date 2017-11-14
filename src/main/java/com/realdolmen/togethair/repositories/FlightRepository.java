package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.Airport;
import com.realdolmen.togethair.domain.Flight;

import java.util.Date;
import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
public interface FlightRepository {
    
    List<Flight> findAll();
    
    Flight findById(Long id);
    
    Flight findByFlightCode(String code);
    
    List<Flight> findFromToOnDate(List<Airport> from, List<Airport> to, Date date1);
    
    List<Flight> findFromToBetweenDates(List<Airport> from, List<Airport> to, Date date1, Date date2);
    
    Flight create(Flight flight);
}
