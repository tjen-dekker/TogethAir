package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.User;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
@Transactional
@ApplicationScoped
@ManagedBean
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User create(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return em.createQuery("select U from User U where email=:email", User.class).setParameter("email", email).getSingleResult();
    }

    @Override
    public String getFirstNameofCurrentUser(String username) {
        return em.createQuery("select U.firstName from User U where email=:userName", String.class).setParameter("userName", username).getSingleResult();
    }

    @Override
    public String getLastNameofCurrentUser(String username) {
        return em.createQuery("select U.lastName from User U where email=:userName", String.class).setParameter("userName", username).getSingleResult();
    }



}
