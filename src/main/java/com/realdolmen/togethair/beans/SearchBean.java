package com.realdolmen.togethair.beans;

import com.realdolmen.togethair.DTO.FlightDTO;
import com.realdolmen.togethair.domain.Flight;
import com.realdolmen.togethair.domain.TravelClass;
import com.realdolmen.togethair.services.SearchServiceBean;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

/**
 * Created by GWTBF10 on 8/11/2017.
 */
@Named
@SessionScoped
public class SearchBean {
	@Inject
	private SearchServiceBean searchService;
	
	public List<FlightDTO> findFromToOnDate(String fromCityName, String toCityName, Date date){
		return searchService.findFromToOnDate(fromCityName,toCityName,date);
	}
	
	public List<FlightDTO> findFromTo(String fromCityName, String toCityName){
		return searchService.findFromTo(fromCityName,toCityName);
	}
	
	public List<FlightDTO> findFromToSortedByDate(String fromCityName, String toCityName){
		return searchService.findFromToSortedByDate(fromCityName,toCityName);
	}
	
	public List<FlightDTO> findFromTo(String fromCityName, String toCityName, TravelClass travelClass, int minNrOfSeats){
		return searchService.findFromTo(fromCityName,toCityName,travelClass,minNrOfSeats);
	}
	
	public FlightDTO findByFlightCode(String flightCode){
		return searchService.findByFlightCode(flightCode);
	}
	
}
