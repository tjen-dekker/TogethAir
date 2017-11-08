package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.Booking;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
public class BookingRepositoryImpl implements BookingRepository{

    @PersistenceContext
    private EntityManager em;

    public List<Booking> findAll(){
        return em.createQuery("select b from Booking b", Booking.class).getResultList();
    }

    public Booking findById(Long id){
        return em.find(Booking.class, id);
    }

    public Booking create(Booking booking){
        em.persist(booking);
        return booking;
    }
}
