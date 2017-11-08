package com.realdolmen.togethair.services;

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
	
	public Flight FindById(Long id){
		return flightRepository.findById(id);
	}
	
	public List<Flight> findAll(){
		return flightRepository.findAll();
	}

	public Flight findByFlightCode(String flightCode){
		return flightRepository.findByFlightCode(flightCode);
	}
	
	public List<Flight> findFromToOnDate(String fromCityName, String toCityName,Date date){
		return flightRepository.findFromToOnDate(getAirportsFomCity(getCityFromName(fromCityName)), getAirportsFomCity(getCityFromName(toCityName)), date);
	}
	
	public List<Flight> findFromTo(String fromCityName, String toCityName){
		return flightRepository.findFromTo(getAirportsFomCity(getCityFromName(fromCityName)), getAirportsFomCity(getCityFromName(toCityName)));
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
	
	public List<Flight> sortByCheapestSeat(List<Flight> flights){
		Collections.sort(flights);
		return flights;
	}
	
	public List<Flight> sortByCheapestSeatOfClass(List<Flight> flights,TravelClass tClass){
		switch(tClass){
			default:
			case ECONOMY:  flights.sort(Flight.cheapestEconomyComparator);
				break;
			case BUSINESS: flights.sort(Flight.cheapestBusinessComparator);
				break;
			case FIRSTCLASS: flights.sort(Flight.cheapestFirstClassComparator);
				break;
		}
		return flights;
	}
	
	public List<Flight> sortByDateTime(List<Flight> flights){
		Collections.sort(flights,Flight.DateTimeComparator);
		return flights;
	}
	
	private City getCityFromName(String cityName){
		return cityRepository.findByName(cityName);
	}
	
	private List<Airport> getAirportsFomCity(City city){
		return airportRepository.findByCity(city);
	}
}
