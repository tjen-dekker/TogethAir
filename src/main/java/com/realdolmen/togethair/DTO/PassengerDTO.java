package com.realdolmen.togethair.DTO;

import com.realdolmen.togethair.Exceptions.SeatAlreadyTakenException;
import com.realdolmen.togethair.domain.Passenger;
import com.realdolmen.togethair.domain.Seat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created by TDKBG57 on 2017-11-08.
 */
public class PassengerDTO {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "This is not a valid last name")
    private String lastName;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "This is not a valid first name")
    private String firstName;
    @NotNull(message = "Please enter a date")
    private Date birthDate;
    @NotNull
    private SeatDTO seat;

    public PassengerDTO(){

    }

    public PassengerDTO(SeatDTO seat) throws SeatAlreadyTakenException {
        setSeat(seat);
    }
	
	public PassengerDTO(Passenger p) throws SeatAlreadyTakenException {
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

    public void setSeat(SeatDTO seat) throws SeatAlreadyTakenException {
        if (seat.isAvailable()) {
            this.seat = seat;
            //seat.setAvailable(false);
        } else
            throw new SeatAlreadyTakenException();
    }

}
