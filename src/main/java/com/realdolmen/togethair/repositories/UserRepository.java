package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.User;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
@ApplicationScoped
public interface UserRepository {

    public List<User> findAll();

    public User findById(Long id);

    public User create(User user);

    public User getUserByEmail(String email);

    String getFirstNameofCurrentUser(String username);

    String getLastNameofCurrentUser(String username);

    String getSaltForUser(String password);
}
