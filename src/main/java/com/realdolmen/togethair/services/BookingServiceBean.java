package com.realdolmen.togethair.services;

import com.realdolmen.togethair.domain.Booking;
import com.realdolmen.togethair.repositories.BookingRepository;

import javax.inject.Inject;

public class BookingServiceBean {
    @Inject
    BookingRepository bookingRepository;

    public void save(Booking booking){
        //TODO should save in repo
//        bookingRepository.save(booking);
    }
}
