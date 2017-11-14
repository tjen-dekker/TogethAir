package com.realdolmen.togethair.services;

import com.realdolmen.togethair.DTO.FlightDTO;
import com.realdolmen.togethair.domain.Airport;
import com.realdolmen.togethair.domain.City;
import com.realdolmen.togethair.domain.Flight;
import com.realdolmen.togethair.domain.TravelClass;
import com.realdolmen.togethair.repositories.AirportRepository;
import com.realdolmen.togethair.repositories.CityRepository;
import com.realdolmen.togethair.repositories.FlightRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by GWTBF10 on 8/11/2017.
 */
public class SearchServiceBean {

	@Inject
	FlightRepository flightRepository;
	
	@Inject
	CityRepository cityRepository;
	
	@Inject
	AirportRepository airportRepository;
	
	public FlightDTO FindById(Long id){
		return new FlightDTO(flightRepository.findById(id));
	}
	
	public List<FlightDTO> findAll(){
		return mapFlightList(flightRepository.findAll());
	}
	
	public FlightDTO findByFlightCode(String flightCode){
		return new FlightDTO(flightRepository.findByFlightCode(flightCode));
	}

	public List<FlightDTO> findFromToBetweenDates(String fromCityName, String toCityName,Date date1, Date date2,TravelClass travelClass,int minNrOfFreeSeats){
		List<Flight> flightList = flightRepository.findFromToBetweenDates(getAirportsFomCity(getCityFromName(fromCityName)), getAirportsFomCity(getCityFromName(toCityName)), date1, date2);
		return mapFlightList(filterMinNrOfFreeSeatsOfClass(flightList,travelClass,minNrOfFreeSeats));
	}
	
	public List<FlightDTO> findFromToOnDate(String fromCityName, String toCityName,Date date1, TravelClass travelClass,int minNrOfFreeSeats){
		List<Flight> flightList = flightRepository.findFromToOnDate(getAirportsFomCity(getCityFromName(fromCityName)), getAirportsFomCity(getCityFromName(toCityName)), date1);
		return mapFlightList(filterMinNrOfFreeSeatsOfClass(flightList,travelClass,minNrOfFreeSeats));
	}

	private List<Flight>filterMinNrOfFreeSeatsOfClass(List<Flight> flights, TravelClass travelClass, int minNrOfFreeSeats){
		List<Flight> filteredList= new ArrayList<>();
		
		for(Flight f : flights){
			if(f.getFreeSeatsOfClass(travelClass)>=minNrOfFreeSeats)
				filteredList.add(f);
		}
		return filteredList;
	}

	private City getCityFromName(String cityName){
		return cityRepository.findByName(cityName);
	}
	
	public List<String> getAllCityNames(){
		List<City> allCities = cityRepository.findAll();
		List<String> allCitesStrings = new ArrayList<>();
		for (City city :allCities) {
			allCitesStrings.add(city.getName());
		}
		return allCitesStrings;
	}
	
	private List<Airport> getAirportsFomCity(City city){
		return airportRepository.findByCity(city);
	}
	
	private List<FlightDTO> mapFlightList(List<Flight> flights){
			List<FlightDTO> result = new ArrayList<>();
			for(Flight f : flights){
				result.add(new FlightDTO(f));
			}
			return result;
	}

}
