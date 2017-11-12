package com.realdolmen.togethair.beans;


import com.realdolmen.togethair.DTO.BookingDTO;
import com.realdolmen.togethair.DTO.FlightDTO;
import com.realdolmen.togethair.DTO.PassengerDTO;
import com.realdolmen.togethair.DTO.SeatDTO;
import com.realdolmen.togethair.Exceptions.SeatAlreadyTakenException;
import com.realdolmen.togethair.domain.Booking;
import com.realdolmen.togethair.domain.Flight;
import com.realdolmen.togethair.domain.Passenger;
import com.realdolmen.togethair.domain.Seat;
import com.realdolmen.togethair.repositories.PassengerRepository;
import com.realdolmen.togethair.services.BookingServiceBean;
import com.realdolmen.togethair.services.FlightServiceBean;
import com.realdolmen.togethair.services.PassengerServiceBean;
import com.realdolmen.togethair.services.PriceCalculationService;

import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@FlowScoped("booking")
public class BookingFlowBean implements Serializable{

    @Inject
    private BookingServiceBean bookingService;
    @Inject
    private FlightServiceBean flightService;
    @Inject
    private PassengerServiceBean passengerService;
    @Inject
    private PriceCalculationService priceCalculationService;

    //TODO should probably use a DTO or DAO who knows
    private BookingDTO booking;
    private FlightDTO flight;
    private Flight f;
    private Booking b;

    private float price;
    private Integer amountOfPassengers;

    //TODO should get booking details from previous search (probably amount of passengers and flight)
    public void prepare(){
        booking = new BookingDTO();
        b= new Booking();
        f = flightService.findById(1L);
        flight = new FlightDTO(f);
        price = priceCalculationService.calculateTotalPrice(b,false);
    }

    public BookingDTO getBooking(){
        return booking;
    }

    public FlightDTO getFlight(){
        return flight;
    }

    //TODO we should probably catch the exception
    public void save() throws SeatAlreadyTakenException {
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
                booking.addPassenger(new PassengerDTO());
            }
        }
    }

    public void recalculate() throws SeatAlreadyTakenException {
        System.out.println("quickmaths");
        //convert BookingDTO to an actual Booking so we can calculate the price
        Booking p = new Booking();
        List<Passenger> ps = new ArrayList<>();
        System.out.println(booking.getPassengers().size());
        for(PassengerDTO pd : booking.getPassengers()){
            Seat s = f.getSeat(pd.getSeat().getLocation());
            System.out.println(s.getPrice());
            Passenger passenger = new Passenger();
            passenger.setSeatWithoutChangingAvailability(s);
            ps.add(passenger);
        }
        p.setPassengers(ps);
        price = priceCalculationService.calculateTotalPrice(p,false);
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
