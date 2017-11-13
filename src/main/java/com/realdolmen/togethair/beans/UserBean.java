package com.realdolmen.togethair.beans;

import com.realdolmen.togethair.domain.User;
import com.realdolmen.togethair.services.UserServiceBean;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;


@ApplicationScoped
public class UserBean {
    private User user;

    @Inject
    UserServiceBean userServiceBean;
}
