package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.Seat;

import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
@ApplicationScoped
@Transactional
public class SeatRepositoryImpl implements SeatRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Seat> findAll() {
        return em.createQuery("select s from Seat s", Seat.class).getResultList();
    }

    @Override
    public Seat findById(Long id) {
        return em.find(Seat.class, id);
    }

    @Override
    public Seat create(Seat seat) {
        em.persist(seat);
        return seat;
    }

    @Override
    public void update(Seat seat) {
        em.merge(seat);
    }
}
