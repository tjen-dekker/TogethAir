package com.realdolmen.togethair.utility;

import com.realdolmen.togethair.DTO.AirportDTO;
import com.realdolmen.togethair.DTO.FlightDTO;
import com.realdolmen.togethair.domain.Airport;
import com.realdolmen.togethair.domain.Flight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GWTBF10 on 8/11/2017.
 */
public class DTOTranslator {
	
	public static List<FlightDTO> mapFlightList(List<Flight> flightList){
		List<FlightDTO> result = new ArrayList<>();
		for(Flight f : flightList){
			result.add(new FlightDTO(f));
		}
		return result;
	}
	
}
