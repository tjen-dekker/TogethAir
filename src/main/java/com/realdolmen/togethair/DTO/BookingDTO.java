package com.realdolmen.togethair.DTO;

import com.realdolmen.togethair.domain.Passenger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-08.
 */
public class BookingDTO {


    private List<Passenger> passengers = new ArrayList<>();

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public void addPassenger(Passenger p){
        passengers.add(p);
    }
}
