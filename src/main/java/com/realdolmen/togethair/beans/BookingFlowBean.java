package com.realdolmen.togethair.beans;


import com.realdolmen.togethair.DTO.BookingDTO;
import com.realdolmen.togethair.DTO.FlightDTO;
import com.realdolmen.togethair.DTO.PassengerDTO;
import com.realdolmen.togethair.DTO.SeatDTO;
import com.realdolmen.togethair.Exceptions.SeatAlreadyTakenException;
import com.realdolmen.togethair.domain.*;
import com.realdolmen.togethair.services.*;
import org.apache.shiro.SecurityUtils;

import javax.faces.context.FacesContext;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
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
    @Inject
    private UserServiceBean userService;

    //TODO should probably use a DTO or DAO who knows
    private BookingDTO booking;
    private FlightDTO flight;
    private Flight f;
    private Booking b;
    private User user;

    private float price;
    private Integer amountOfPassengers;

    //TODO should get booking details from previous search (probably amount of passengers and flight)
    public void prepare(){
        //if(SecurityUtils.getSubject().isAuthenticated())
        if(SecurityUtils.getSubject().getPrincipal() != null){
            user = userService.getUserByEmail(SecurityUtils.getSubject().getPrincipal().toString());
        }
        //String flightId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("flightId");
        Iterator<String> it = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterNames();
        Map<String,String> parameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String flightId="";
        while(it.hasNext()){
            FacesContext.getCurrentInstance().getExternalContext().getRequestParameterNames();
            String param = it.next();
            if(param.contains("flightId")){
                flightId = parameters.get(param);
            }
            else if(param.contains("amountOfPassengers")){
                amountOfPassengers = Integer.parseInt(parameters.get(param));
            }
        }

        booking = new BookingDTO();
        b= new Booking();
        f = flightService.findById(Long.parseLong(flightId));
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
                Passenger passenger = new Passenger(p.getFirstName(),p.getLastName(),p.getBirthDate(),s);
                b.addPassenger(passenger);
            }
            //TODO there should be checks, can we check stuff after every step or only at the end?

            if(user!=null){
                user.addBooking(b);
                userService.update(user);
            }
            else{
                bookingService.create(b);
            }

        } catch (SeatAlreadyTakenException ex){
            resetBooking();
            throw new SeatAlreadyTakenException();
        } catch (ConstraintViolationException ex){
            resetBooking();
            System.out.println(ex.getMessage());

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
            //TODO throw a new exception not enough free seats
        }
        if(amountOfPassengers==0){
            //TODO throw another exception that says that a booking should have passengers
        }
        if(amountOfPassengers > passengers.size() ) {
            Iterator<Seat> iterator = availableSeats.iterator();
            if(SecurityUtils.getSubject().getPrincipal()!=null) {
                PassengerDTO user = new PassengerDTO();
                user.setFirstName(this.user.getFirstName());
                user.setLastName(this.user.getLastName());
                user.setSeat(new SeatDTO(iterator.next()));
                passengers.add(user);
            }

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
