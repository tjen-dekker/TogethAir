package com.realdolmen.togethair.services;

import com.realdolmen.togethair.DTO.BookingDTO;
import com.realdolmen.togethair.DTO.PassengerDTO;
import com.realdolmen.togethair.DTO.SeatDTO;
import com.realdolmen.togethair.domain.Booking;
import com.realdolmen.togethair.domain.Passenger;
import com.realdolmen.togethair.domain.Seat;
import com.realdolmen.togethair.repositories.BookingRepository;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ApplicationScoped
public class BookingServiceBean implements Serializable {
    @Inject
    BookingRepository bookingRepository;


    public void create(Booking booking){
        bookingRepository.create(booking);
    }
}
