package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.Airport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class AirportRepositoryImpl implements AirportRepository{

    private EntityManager em;

    public List<Airport> findAll(){
        return em.createQuery("select a from Airport a", Airport.class).getResultList();
    }

    public Airport findById(Long id){
        return em.find(Airport.class, id);
    }

    public Airport create(Airport airport){
        em.persist(airport);
        return airport;
    }

    public AirportRepositoryImpl() {
    }

    public AirportRepositoryImpl(EntityManager em) {
        this.em = em;
    }
}
