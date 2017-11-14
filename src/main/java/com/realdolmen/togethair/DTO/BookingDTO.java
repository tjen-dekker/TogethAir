package com.realdolmen.togethair.DTO;

import com.realdolmen.togethair.Exceptions.SeatAlreadyTakenException;
import com.realdolmen.togethair.domain.Booking;
import com.realdolmen.togethair.domain.Passenger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-08.
 */
public class BookingDTO {
    
    private List<PassengerDTO> passengers = new ArrayList<>();

    public BookingDTO(){}
    
    public BookingDTO(Booking booking) throws SeatAlreadyTakenException {
        List<Passenger> passengerList = booking.getPassengers();
        for (Passenger p: passengerList ) {
            addPassenger(new PassengerDTO(p));
            
        }
    }
    
    public List<PassengerDTO> getPassengers() {
        return passengers;
    }
    
    public void setPassengers(List<PassengerDTO> passengers) {
        this.passengers = passengers;
    }
    
    public void addPassenger(PassengerDTO passenger){
        passengers.add(passenger);
    }
}
