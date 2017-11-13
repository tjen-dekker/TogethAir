package com.realdolmen.togethair.repositories;


import com.realdolmen.togethair.domain.Role;


import java.util.List;

public interface RolesRepository {

    List<Role> findAll();

    Role getByName(String roleName);

    Role create(Role role);

}
