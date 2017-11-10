package com.realdolmen.togethair.beans;


import com.realdolmen.togethair.DTO.BookingDTO;
import com.realdolmen.togethair.DTO.FlightDTO;
import com.realdolmen.togethair.DTO.PassengerDTO;
import com.realdolmen.togethair.DTO.SeatDTO;
import com.realdolmen.togethair.domain.Booking;
import com.realdolmen.togethair.domain.Flight;
import com.realdolmen.togethair.domain.Passenger;
import com.realdolmen.togethair.domain.Seat;
import com.realdolmen.togethair.repositories.PassengerRepository;
import com.realdolmen.togethair.services.BookingServiceBean;
import com.realdolmen.togethair.services.FlightServiceBean;
import com.realdolmen.togethair.services.PassengerServiceBean;

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

    @Inject
    private PassengerServiceBean passengerService;

    //TODO should probably use a DTO or DAO who knows
    private BookingDTO booking;
    private FlightDTO flight;
    private Flight f;
    private Booking b;

    private Integer amountOfPassengers;

    //TODO should get booking details from previous search (probably amount of passengers and flight)
    public void prepare(){
        booking = new BookingDTO();
        b= new Booking();
        f = flightService.findById(1L);
        flight = new FlightDTO(f);

    }

    public BookingDTO getBooking(){
        return booking;
    }

    public FlightDTO getFlight(){
        return flight;
    }

    public void save(){
        for(PassengerDTO p : booking.getPassengers()){
            Seat s = f.getSeat(p.getSeat().getLocation());
            Passenger passenger = new Passenger(p.getFirstName(),p.getLastName(),p.getBirthDate(),s);
            passengerService.create(passenger);
            b.addPassenger(passenger);
        }
        //TODO there should be checks, can we check stuff after every step or only at the end?
        bookingService.create(b);
    }

    public void setAmountOfPassengers(Integer amount){
        this.amountOfPassengers = amount;
    }

    public Integer getAmountOfPassengers(){
        return this.amountOfPassengers;
    }

    public void createPassengers(){
        if(amountOfPassengers != booking.getPassengers().size() ) {
            for (int i = 0; i < amountOfPassengers; i++) {
                //TODO change this pls; seat shouldnt be created here
//                PassengerDTO passengerDTO = new PassengerDTO();
//                passengerDTO.setSeat(new SeatDTO());
                booking.addPassenger(new PassengerDTO());
            }
        }
    }

}
