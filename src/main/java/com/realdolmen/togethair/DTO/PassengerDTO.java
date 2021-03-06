package com.realdolmen.togethair.DTO;

import com.realdolmen.togethair.domain.Passenger;
import com.realdolmen.togethair.domain.Seat;

import java.util.Date;

/**
 * Created by TDKBG57 on 2017-11-08.
 */
public class PassengerDTO {


    private String lastName;
    private String firstName;
    private Date birthDate;
    private SeatDTO seat;

    public PassengerDTO(){

    }

    public PassengerDTO(SeatDTO seat){
        setSeat(seat);
    }
	
	public PassengerDTO(Passenger p) {
	    setBirthDate(p.getBirthDate());
	    setFirstName(p.getFirstName());
	    setLastName(p.getlastName());
	    setSeat(new SeatDTO(p.getSeat()));
	}
	
	public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
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

    public SeatDTO getSeat() {
        return seat;
    }

    public void setSeat(SeatDTO seat) {
        seat.setAvailable(false);
        this.seat = seat;
    }
}
