package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.Country;

import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
public interface CountryRepository {

    public List<Country> findAll();

    public Country findById(Long id);

    public Country create(Country country);
}
