package com.realdolmen.togethair.services;


import com.realdolmen.togethair.domain.Booking;
import com.realdolmen.togethair.domain.User;
import com.realdolmen.togethair.repositories.UserRepository;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@ApplicationScoped
public class UserServiceBean implements Serializable{

    @Inject
    UserRepository repository;

    public User getUserByEmail(String email){
        return repository.getUserByEmail(email);
    }

    public List<Booking> getAllBookingsFromUser(String email){
        return repository.findAllBookingsForUser(email);

    }

    public void update(User u){
        repository.update(u);
    }
}
