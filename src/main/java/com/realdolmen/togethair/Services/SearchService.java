package com.realdolmen.togethair.Services;

import com.realdolmen.togethair.domain.Airport;
import com.realdolmen.togethair.domain.City;
import com.realdolmen.togethair.domain.Flight;
import com.realdolmen.togethair.domain.TravelClass;
import com.realdolmen.togethair.repositories.AirportRepository;
import com.realdolmen.togethair.repositories.CityRepository;
import com.realdolmen.togethair.repositories.FlightRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GWTBF10 on 8/11/2017.
 */
public class SearchService {

	@Inject
	FlightRepository flightRepository;
	
	@Inject
	CityRepository cityRepository;
	
	@Inject
	AirportRepository airportRepository;
	
	public Flight FindById(Long id){
		return flightRepository.findById(id);
	}
	
	public List<Flight> findAll(){
		return flightRepository.findAll();
	}

	public Flight findByFlightCode(String flightCode){
		return flightRepository.findByFlightCode(flightCode);
	}
	
	public List<Flight> findFromTo(String fromCityName, String toCityName){
		return flightRepository.findFromTo(getAirportsFomCity(getCityFromName(fromCityName)), getAirportsFomCity(getCityFromName(toCityName)));
	}
	
	public List<Flight> findFrom(String cityName){
		return flightRepository.findFrom(getAirportsFomCity(getCityFromName(cityName)));
	}
	
	public List<Flight> findTo(String cityName){
		return flightRepository.findTo(getAirportsFomCity(getCityFromName(cityName)));
	}
	
	public List<Flight> filterMinNrOfFreeSeatsOfClass(List<Flight> flights, TravelClass travelClass, int minNrOfFreeSeats){
		List<Flight> filteredList= new ArrayList<>();
		
		for(Flight f : flights){
			if(f.getFreeSeatsOfClass(travelClass)>=minNrOfFreeSeats)
				filteredList.add(f);
		}
		return filteredList;
	}
	
	public List<Flight> filterMinNrOfFreeSeats(List<Flight> flights, int minNrOfFreeSeats){
		List<Flight> filteredList= new ArrayList<>();
		
		for(Flight f : flights){
			if(f.getFreeSeats()>=minNrOfFreeSeats)
				filteredList.add(f);
		}
		return filteredList;
	}
	
	private City getCityFromName(String cityName){
		return cityRepository.findByName(cityName);
	}
	
	private List<Airport> getAirportsFomCity(City city){
		return airportRepository.findByCity(city);
	}
}
