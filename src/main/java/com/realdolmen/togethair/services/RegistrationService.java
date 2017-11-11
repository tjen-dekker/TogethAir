package com.realdolmen.togethair.services;

import com.realdolmen.togethair.domain.Role;
import com.realdolmen.togethair.domain.User;
import com.realdolmen.togethair.repositories.RolesRepository;
import com.realdolmen.togethair.repositories.UserRepository;
import org.apache.shiro.crypto.hash.Sha256Hash;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class RegistrationService implements Serializable {
    @Inject
    private UserRepository userRepository;

    @Inject
    private RolesRepository rolesRepository;

    @Inject
    private LoginService loginService;


    private String email;
    private String firstName;
    private String lastName;
    private String plainTextPassword;
    private String hashedPassword;
    private boolean isAdmin;


    private void generatePassword(User user, String plainTextPassword) {
//        RandomNumberGenerator rng = new SecureRandomNumberGenerator();
//        Object salt = rng.nextBytes();
        // Now hash the plain-text password with the random salt and multiple
        // iterations and then Base64-encode the value (requires less space than
        // Hex):
        setHashedPassword(new Sha256Hash(plainTextPassword).toBase64());

        user.setPassword(hashedPassword);
//        user.setSalt(salt.toString());
    }

    public void register() {
        User user = new User();

        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);


        generatePassword(user, plainTextPassword);


        System.err.println("User with email:" + user.getEmail()
                + " hashedPassword:" + user.getPassword() + " salt:"
                + user.getSalt());

        if (isAdmin) {
            Role role = new Role();
            role.setEmail(email);
            role.setRoleName("admin");
            rolesRepository.create(role);
        }

        userRepository.create(user);

        loginService.setPassword(plainTextPassword);
        loginService.setUsername(email);
        loginService.setRememberMe(false);

        loginService.doLogin();

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlainTextPassword() {
        return plainTextPassword;
    }

    public void setPlainTextPassword(String plainTextPassword) {
        this.plainTextPassword = plainTextPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}

