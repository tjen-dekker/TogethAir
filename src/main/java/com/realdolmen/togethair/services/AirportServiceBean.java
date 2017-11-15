package com.realdolmen.togethair.services;

import com.realdolmen.togethair.domain.Airport;
import com.realdolmen.togethair.domain.City;
import com.realdolmen.togethair.domain.Country;
import com.realdolmen.togethair.repositories.AirportRepository;
import com.realdolmen.togethair.repositories.CityRepository;
import com.realdolmen.togethair.repositories.CountryRepository;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AirportServiceBean implements Serializable {

    @Inject
    AirportRepository airportRepository;

    @Inject
    CityRepository cityRepository;

    @Inject
    CountryRepository countryRepository;

    private List<Airport> allAirPort = new ArrayList<>();
    private List<City> allCities = new ArrayList<>();
    private List<Country> allCountries = new ArrayList<>();

    public void saveAirport(Airport airport){
        airportRepository.create(airport);
    }

    public List<String> allCityNames() {
        List<String> cityNames = new ArrayList<>();
        setAllCities(cityRepository.findAll());
        for (City allCity : allCities) {
            cityNames.add(allCity.getName());
        }

        return cityNames;
    }

    public List<String> allCountryNames() {
        List<String> countryNames = new ArrayList<>();
        setAllCountries(countryRepository.findAll());
        for (Country country : allCountries) {
            countryNames.add(country.getName());
        }

        return countryNames;
    }

    public List<String> allAirportNames() {
        List<String> airportNames = new ArrayList<>();
        setAllAirPort(airportRepository.findAll());
        for (Airport airport : allAirPort) {
            airportNames.add(airport.getName());
        }

        return airportNames;
    }

    public Country countryByName(String name) {
        return countryRepository.findByName(name);
    }

    public City cityByName(String name) {
        return cityRepository.findByName(name);
    }

    public Airport airportByName(String name) {
        return airportRepository.getByName(name);
    }


    public List<Airport> getAllAirPort() {
        return allAirPort;
    }

    public void setAllAirPort(List<Airport> allAirPort) {
        this.allAirPort = allAirPort;
    }

    public List<City> getAllCities() {
        return allCities;
    }

    public void setAllCities(List<City> allCities) {
        this.allCities = allCities;
    }

    public List<Country> getAllCountries() {
        return allCountries;
    }

    public void setAllCountries(List<Country> allCountries) {
        this.allCountries = allCountries;
    }


    public List<Country> allCountries() {
        return countryRepository.findAll();
    }

    public List<City> allCities() {
        return cityRepository.findAll();
    }

    public List<Airport> allAirports() {
        return airportRepository.findAll();
    }

    public void SaveCountry(Country c) {
        countryRepository.create(c);
    }

    public void SaveCity(City c) {
        cityRepository.create(c);
    }

    public void SaveAriport(Airport a) {
        airportRepository.create(a);
    }

}
