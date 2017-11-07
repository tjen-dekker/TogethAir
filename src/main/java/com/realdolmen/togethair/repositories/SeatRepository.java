package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.Seat;

import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
public interface SeatRepository {

    public List<Seat> findAll();

    public Seat findById(Long id);

    public Seat create(Seat seat);
}
