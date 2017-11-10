package com.realdolmen.togethair.beans;


import com.realdolmen.togethair.domain.Booking;
import com.realdolmen.togethair.domain.Flight;
import com.realdolmen.togethair.domain.Passenger;
import com.realdolmen.togethair.services.BookingServiceBean;
import com.realdolmen.togethair.services.FlightServiceBean;

import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@FlowScoped("booking")
public class BookingFlowBean implements Serializable{

    @Inject
    private BookingServiceBean bookingService;
    @Inject
    private FlightServiceBean flightService;

    //TODO should probably use a DTO or DAO who knows
    private Booking booking;
    private Flight flight;

    //TODO should get booking details from previous search (probably amount of passengers and flight)
    public void prepare(){
        booking = new Booking();
        flight = flightService.findById(1L);//new Flight();
        if(booking.getPassengers()==null||booking.getPassengers().isEmpty()) {
            //TODO change this so the new passenger uses info of the user that is logged in
            //TODO should probably use a DTO or DAO who knows
            Passenger p = new Passenger();
            p.setFirstName("michael");
            p.setlastName("newbould");
            p.setBirthDate(new Date());
            booking.addPassenger(p);
            booking.addPassenger(new Passenger());
        }
        else{
            //use existing ones
        }
            //prob init more fields
    }

    public Booking getBooking(){
        return booking;
    }

    public Flight getFlight(){
        return flight;
    }

    public void save(){
        //TODO there should be checks, can we check stuff after every step or only at the end?
        bookingService.save(booking);
    }


}
