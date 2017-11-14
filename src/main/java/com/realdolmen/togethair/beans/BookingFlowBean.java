package com.realdolmen.togethair.beans;


import com.realdolmen.togethair.DTO.BookingDTO;
import com.realdolmen.togethair.DTO.FlightDTO;
import com.realdolmen.togethair.DTO.PassengerDTO;
import com.realdolmen.togethair.DTO.SeatDTO;
import com.realdolmen.togethair.Exceptions.NotEnoughSeatsAvailableException;
import com.realdolmen.togethair.Exceptions.PassengerListIsemptyException;
import com.realdolmen.togethair.Exceptions.SeatAlreadyTakenException;
import com.realdolmen.togethair.domain.*;
import com.realdolmen.togethair.services.*;
import org.apache.shiro.SecurityUtils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
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
    @Inject
    private PaymentBean paymentBean;

    //TODO should probably use a DTO or DAO who knows
    private BookingDTO booking;
    private FlightDTO flight;
    private Flight f;
    private Booking b;
    private User user;
    private String paymentMethod = "credittransfer";


    private float price;
    private Integer amountOfPassengers = 1;

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

    public String validate() throws SeatAlreadyTakenException {
        recalculate();
        save();
        return paymentBean.validate();

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
                user.setBookings(userService.getAllBookingsFromUser(user.getEmail()));
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

    public String createPassengers() throws NotEnoughSeatsAvailableException, PassengerListIsemptyException, SeatAlreadyTakenException, IOException {
        Set<Seat> availableSeats = f.getAvailableSeats();
        List<PassengerDTO> passengers = booking.getPassengers();
        if(f.getAvailableSeats().size()<amountOfPassengers){
            //TODO should use i18n
            facesError("There are not enough seats available");
            //throw new NotEnoughSeatsAvailableException("There are not enough seats available");
            return "detail";
        }
        if(amountOfPassengers<=0){
            //TODO should use i18n
            facesError("You should select at least 1 passenger");
//            throw new PassengerListIsemptyException("You should select at least 1 passenger");
            return "detail";
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
        return "passengers";
    }

    public void recalculate() throws SeatAlreadyTakenException {
        boolean credit = false;
        if("creditcard".equals(paymentMethod)){
            credit = true;
        }
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
        price = priceCalculationService.calculateTotalPrice(p,credit);
        System.out.println("1 " + price);
        b.setTotalPrice(price);
        System.out.println("2 " + b.getTotalPrice());
    }

    private void facesError(String message) throws IOException {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    public String goToPayment() throws SeatAlreadyTakenException {
        if("creditcard".equals(paymentMethod)){
            return "payment";
        }
        else {
            //TODO make page for transfer
            return "";
        }
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) throws SeatAlreadyTakenException {
        this.paymentMethod = paymentMethod;
        recalculate();
    }

    public PaymentBean getPaymentBean() {
        return paymentBean;
    }

    public void setPaymentBean(PaymentBean paymentBean) {
        this.paymentBean = paymentBean;
    }
}
