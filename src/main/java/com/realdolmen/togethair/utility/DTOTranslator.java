package com.realdolmen.togethair.utility;

import com.realdolmen.togethair.DTO.FlightDTO;
import com.realdolmen.togethair.domain.Flight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GWTBF10 on 8/11/2017.
 */
public class DTOTranslator {
	public static FlightDTO mapFlight(Flight f){
		FlightDTO result = new FlightDTO();
		
		result.setDepartureDateTime(f.getDepartureDateTime());
		result.setDuration(f.getDuration());
		result.setFlightCode(f.getFlightCode());
		result.setFlightCompany(f.getFlightCompany());
		result.setFrom(f.getFrom());
		result.setTo(f.getTo());
		result.setSeats(f.getSeats());
		
		return result;
	}
	
	public static List<FlightDTO> mapFlightList(List<Flight> flightList){
		List<FlightDTO> result = new ArrayList<>();
		for(Flight f : flightList){
			result.add(mapFlight(f));
		}
		return result;
	}
}
