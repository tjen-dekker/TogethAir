package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.Country;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
@Named("countryServiceImpl")
@Transactional
public class CountryRepositoryImpl implements CountryRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Country findByName(String name) {
        return em.createQuery("select C from Country C where C.name = :name", Country.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Country> findAll(){
        return em.createQuery("select c from Country c", Country.class).getResultList();
    }

    public Country findById(Long id){
        return em.find(Country.class, id);
    }

    public Country create(Country country){
        em.persist(country);
        return country;
    }

    @Override
    public void merge(Country country) {
        em.merge(country);
    }
}
