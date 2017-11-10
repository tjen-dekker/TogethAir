package com.realdolmen.togethair.services;

import com.realdolmen.togethair.domain.Booking;
import com.realdolmen.togethair.repositories.BookingRepository;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ApplicationScoped
public class BookingServiceBean implements Serializable {
    @Inject
    BookingRepository bookingRepository;

    public void save(Booking booking){
        //TODO should save in repo
//        bookingRepository.save(booking);
    }
}
