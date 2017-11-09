package com.realdolmen.togethair.services;

import com.realdolmen.togethair.DTO.FlightDTO;
import com.realdolmen.togethair.domain.Airport;
import com.realdolmen.togethair.domain.City;
import com.realdolmen.togethair.domain.Flight;
import com.realdolmen.togethair.domain.TravelClass;
import com.realdolmen.togethair.repositories.AirportRepository;
import com.realdolmen.togethair.repositories.CityRepository;
import com.realdolmen.togethair.repositories.FlightRepository;
import com.realdolmen.togethair.utility.DTOTranslator;

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
		return DTOTranslator.mapFlight(flightRepository.findById(id));
	}
	
	public List<FlightDTO> findAll(){
		return DTOTranslator.mapFlightList(flightRepository.findAll());
	}

	public FlightDTO findByFlightCode(String flightCode){
		return DTOTranslator.mapFlight(flightRepository.findByFlightCode(flightCode));
	}
	
	public List<FlightDTO> findFromToOnDate(String fromCityName, String toCityName,Date date){
		List<Flight> flightList = flightRepository.findFromToOnDate(getAirportsFomCity(getCityFromName(fromCityName)), getAirportsFomCity(getCityFromName(toCityName)), date);
		sortByCheapestSeat(flightList);
		return mapToDTOList(flightList);
	}
	
	public List<FlightDTO> findFromTo(String fromCityName, String toCityName){
		List<Flight> flightList = flightRepository.findFromTo(getAirportsFomCity(getCityFromName(fromCityName)), getAirportsFomCity(getCityFromName(toCityName)));
		sortByCheapestSeat(flightList);
		return mapToDTOList(flightList);
	}
	
	public List<FlightDTO> findFromToSortedByDate(String fromCityName, String toCityName){
		List<Flight> flightList = flightRepository.findFromTo(getAirportsFomCity(getCityFromName(fromCityName)), getAirportsFomCity(getCityFromName(toCityName)));
		sortByDateTime(flightList);
		return mapToDTOList(flightList);
	}
	
	public List<FlightDTO> findFromTo(String fromCityName, String toCityName,TravelClass travelClass,int minNrOfFreeSeats){
		List<Flight> flightList = flightRepository.findFromTo(getAirportsFomCity(getCityFromName(fromCityName)), getAirportsFomCity(getCityFromName(toCityName)));
		filterMinNrOfFreeSeatsOfClass(flightList,travelClass,minNrOfFreeSeats);
		sortByCheapestSeatOfClass(flightList,travelClass);
		return mapToDTOList(flightList);
	}
	
	public List<FlightDTO> findFromToSortedByDate(String fromCityName, String toCityName,TravelClass travelClass,int minNrOfFreeSeats){
		List<Flight> flightList = flightRepository.findFromTo(getAirportsFomCity(getCityFromName(fromCityName)), getAirportsFomCity(getCityFromName(toCityName)));
		filterMinNrOfFreeSeatsOfClass(flightList,travelClass,minNrOfFreeSeats);
		sortByDateTime(flightList);
		return mapToDTOList(flightList);
	}
	
	private List<Flight>filterMinNrOfFreeSeatsOfClass(List<Flight> flights, TravelClass travelClass, int minNrOfFreeSeats){
		List<Flight> filteredList= new ArrayList<>();
		
		for(Flight f : flights){
			if(f.getFreeSeatsOfClass(travelClass)>=minNrOfFreeSeats)
				filteredList.add(f);
		}
		return filteredList;
	}
	
	private List<FlightDTO> mapToDTOList(List<Flight> flightList){
		return DTOTranslator.mapFlightList(flightList);
	}

	
	private List<Flight> sortByCheapestSeat(List<Flight> flights){
		Collections.sort(flights);
		return flights;
	}
	
	public List<Flight> sortByCheapestSeatOfClass(List<Flight> flights,TravelClass tClass) {
		switch (tClass) {
			default:
				case ECONOMY:flights.sort(Flight.cheapestEconomyComparator);
				break;
			case BUSINESS:flights.sort(Flight.cheapestBusinessComparator);
				break;
			case FIRSTCLASS:flights.sort(Flight.cheapestFirstClassComparator);
				break;
		}
		return flights;
	}
	
	private List<Flight> sortByDateTime(List<Flight> flights){
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
