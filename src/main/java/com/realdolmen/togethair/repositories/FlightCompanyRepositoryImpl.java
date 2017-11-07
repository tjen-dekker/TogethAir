package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.FlightCompany;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
public class FlightCompanyRepositoryImpl implements FlightCompanyRepository{

    private EntityManager em;

    @Override
    public List<FlightCompany> findAll() {
        return em.createQuery("select fc from FlightCompany fc", FlightCompany.class).getResultList();
    }

    @Override
    public FlightCompany findById(Long id) {
        return em.find(FlightCompany.class, id);
    }

    @Override
    public FlightCompany create(FlightCompany flightCompany) {
        em.persist(flightCompany);
        return flightCompany;
    }
}
