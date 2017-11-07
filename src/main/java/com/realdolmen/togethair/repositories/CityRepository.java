package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.City;

import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
public interface CityRepository {

    public List<City> findAll();

    public City findById(Long id);

    public City create(City city);
}
