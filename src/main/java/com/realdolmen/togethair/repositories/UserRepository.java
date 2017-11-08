package com.realdolmen.togethair.repositories;

import com.realdolmen.togethair.domain.User;

import java.util.List;

/**
 * Created by TDKBG57 on 2017-11-07.
 */
public interface UserRepository {

    public List<User> findAll();

    public User findById(Long id);

    public User create(User user);
}