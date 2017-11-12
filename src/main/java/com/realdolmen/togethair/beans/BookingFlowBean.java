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
import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.util.*;

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
    public void save() throws SeatAlreadyTakenException, ConstraintViolationException {
        try{
            for(PassengerDTO p : booking.getPassengers()){
                Seat s = f.getSeat(p.getSeat().getLocation());
                //TODO BUG: when trying to save the availability of a seat will set on false however if something goes wrong with persisting the passenger (ie empty name) the seat will be seen as unavailable
                Passenger passenger = new Passenger(p.getFirstName(),p.getLastName(),p.getBirthDate(),s);
                b.addPassenger(passenger);
            }
            //TODO there should be checks, can we check stuff after every step or only at the end?
            bookingService.create(b);

        } catch (SeatAlreadyTakenException ex){
            resetBooking();
            throw new SeatAlreadyTakenException();
        } catch (ConstraintViolationException ex){
            resetBooking();
            throw new ConstraintViolationException(ex.getConstraintViolations());
        }
    }

    private void resetBooking(){
        for(Passenger p : b.getPassengers()){
            p.getSeat().setAvailable(true);
        }
        b.setPassengers(new ArrayList<>());
    }

    public void setAmountOfPassengers(Integer amount){
        this.amountOfPassengers = amount;
    }

    public Integer getAmountOfPassengers(){
        return this.amountOfPassengers;
    }

    public void createPassengers(){
        Set<Seat> availableSeats = f.getAvailableSeats();
        List<PassengerDTO> passengers = booking.getPassengers();
        if(f.getAvailableSeats().size()<amountOfPassengers){
            //TODO throw a new exception
        }
        if(amountOfPassengers > passengers.size() ) {
            //booking.setPassengers(new ArrayList<>()); //TODO change now it will remove all user input
            Iterator<Seat> iterator = availableSeats.iterator();
            for (int i = passengers.size(); i < amountOfPassengers; i++) {
                Seat s = iterator.next();
                booking.addPassenger(new PassengerDTO(new SeatDTO(s)));
            }
        }
        else {
            for(int i = passengers.size()-1; passengers.size()-1==amountOfPassengers; i--){
                passengers.remove(i);
            }
        }
    }

    public void recalculate() throws SeatAlreadyTakenException {
        //convert BookingDTO to an actual Booking so we can calculate the price
        Booking p = new Booking();
        List<Passenger> ps = new ArrayList<>();
        for(PassengerDTO pd : booking.getPassengers()){
            Seat s = f.getSeat(pd.getSeat().getLocation());
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
