package com.realdolmen.togethair.beans;


import com.realdolmen.togethair.domain.Booking;
import com.realdolmen.togethair.domain.Passenger;

import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@FlowScoped("booking")
public class BookingFlowBean implements Serializable{

    //TODO should probably use a DTO or DAO who knows
    private Booking booking;

    //TODO should get booking details from previous search
    public void prepare(){
        booking = new Booking();
        if(booking.getPassengers()==null||booking.getPassengers().isEmpty()) {
            //TODO change this so the new passenger uses info of the user that is logged in
            //TODO should probably use a DTO or DAO who knows
            Passenger p = new Passenger();
            p.setFirstName("michael");
            p.setlastName("newbould");
            p.setBirthDate(new Date());
            booking.addPassenger(p);
        }
        else{
            //use existing ones
        }
            //prob init more fields
    }

    public Booking getBooking(){
        return booking;
    }

    public void save(){

    }


}
