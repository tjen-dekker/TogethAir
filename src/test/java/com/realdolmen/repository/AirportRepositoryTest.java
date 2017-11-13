package com.realdolmen.repository;

import com.realdolmen.AbstractRepositoryTest;
import com.realdolmen.togethair.domain.Airport;
import com.realdolmen.togethair.domain.City;
import com.realdolmen.togethair.domain.Country;
import com.realdolmen.togethair.domain.Region;
import com.realdolmen.togethair.repositories.AirportRepository;
import com.realdolmen.togethair.repositories.AirportRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class AirportRepositoryTest extends AbstractRepositoryTest {

    private AirportRepository airportRepository;

    @Before
    public void initializeRepository() {

        airportRepository = new AirportRepositoryImpl(em);
    }

    @Test
    public void doesRepositoryPersist() throws Exception {
        Airport airport = new Airport();
        Country country = new Country();
        City city = new City();
        city.setCountry(country);
        country.setName("Belgium");
        country.setRegion(Region.WESTERN_EUROPE);
        airport.setCode("ABA125");
        airport.setCity(city);
        airportRepository.create(airport);
        Airport airport1 = em.find(Airport.class, airport.getId());
        assertNotNull(airport1);
        assertEquals("ABA125", airport1.getCode());
    }
}



