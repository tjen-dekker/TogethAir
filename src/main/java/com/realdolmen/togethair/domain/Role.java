package com.realdolmen.togethair.domain;

import javax.persistence.*;

/**
 * Created by TDKBG57 on 2017-11-09.
 */
@Entity
@Table
public class Role {private static final long serialVersionUID = -5401510492152424643L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String roleName;

    @Column
    private String email;

    public Role() {
    }

    public String getEmail() {
        return email;
    }

    public Integer getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
