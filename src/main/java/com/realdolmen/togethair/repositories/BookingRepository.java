package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.Booking;

import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
public interface BookingRepository {

    public List<Booking> findAll();

    public Booking findById(Long id);

    public Booking create(Booking booking);
}
