package com.realdolmen.togethair.DTO;

import com.realdolmen.togethair.domain.Seat;

import java.util.Date;

/**
 * Created by TDKBG57 on 2017-11-08.
 */
public class PassengerDTO {


    private String lastName;
    private String firstName;
    private Date birthDate;
    private Seat seat;


    public String getlastName() {
        return lastName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {return birthDate;}

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        //TODO make sure the seat is actually available
        this.seat = seat;
        //todo put in service
        seat.setAvailable(false);
    }
}
