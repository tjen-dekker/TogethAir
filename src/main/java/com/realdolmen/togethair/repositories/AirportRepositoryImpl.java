package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.Airport;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

public class AirportRepositoryImpl implements AirportRepository {

    private EntityManager em;

    @Inject
    Logger logger;

    public List<Airport> findAll() {
        logger.info("finding all airports");
        return em.createQuery("select a from Airport a", Airport.class).getResultList();
    }

    public Airport findById(Long id) {
        logger.info("finding airport by id");
        return em.find(Airport.class, id);
    }

    public Airport create(Airport airport) {
        em.persist(airport);
        logger.info("persisting airport");
        return airport;
    }

    public AirportRepositoryImpl() {
    }

    public AirportRepositoryImpl(EntityManager em) {
        this.em = em;
    }
}