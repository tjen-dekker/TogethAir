package com.realdolmen.togethair.beans;


import com.realdolmen.togethair.domain.Booking;
import com.realdolmen.togethair.domain.Flight;
import com.realdolmen.togethair.domain.Passenger;
import com.realdolmen.togethair.repositories.PassengerRepository;
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

    @Inject //TODO create service and use that instead of directly using the repo
    private PassengerRepository repository;

    //TODO should probably use a DTO or DAO who knows
    private Booking booking;
    private Flight flight;

    private Integer amountOfPassengers;

    //TODO should get booking details from previous search (probably amount of passengers and flight)
    public void prepare(){
        booking = new Booking();
        flight = flightService.findById(1L);//new Flight();

    }

    public Booking getBooking(){
        return booking;
    }

    public Flight getFlight(){
        return flight;
    }

    public void save(){
        for(Passenger p : booking.getPassengers()){
            repository.create(p);
        }
        //TODO there should be checks, can we check stuff after every step or only at the end?
        bookingService.create(booking);
    }

    public void setAmountOfPassengers(Integer amount){
        this.amountOfPassengers = amount;
    }

    public Integer getAmountOfPassengers(){
        return this.amountOfPassengers;
    }

    public void createPassengers(){
        for(int i = 0; i < amountOfPassengers; i++){
            booking.addPassenger(new Passenger());
        }
    }

}
