package com.realdolmen.togethair.services;


import com.realdolmen.togethair.domain.User;
import com.realdolmen.togethair.repositories.UserRepository;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ApplicationScoped
public class UserServiceBean implements Serializable{

    @Inject
    UserRepository repository;

    public User getUserByEmail(String email){
        return repository.getUserByEmail(email);
    }

    public void update(User u){
        repository.update(u);
    }
}
