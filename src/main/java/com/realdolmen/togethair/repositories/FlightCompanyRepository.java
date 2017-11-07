package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.FlightCompany;

import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
public interface FlightCompanyRepository {

    public List<FlightCompany> findAll();

    public FlightCompany findById(Long id);

    public FlightCompany create(FlightCompany flightCompany);
}
