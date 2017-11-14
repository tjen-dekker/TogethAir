package com.realdolmen.togethair.beans;

import com.realdolmen.togethair.domain.User;
import com.realdolmen.togethair.services.UserServiceBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ManagedBean
@SessionScoped
public class UserBean {

    private User user;

    @Inject
    UserServiceBean userServiceBean;

    @RequiresUser
    public void initUser(){
        System.out.println("user is ier");
        user = userServiceBean.getUserByEmail(SecurityUtils.getSubject().getPrincipal().toString());
        user.setBookings(userServiceBean.getAllBookingsFromUser(user.getEmail()));
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
