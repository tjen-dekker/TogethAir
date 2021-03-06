package com.realdolmen.togethair.services;


import com.realdolmen.togethair.domain.Role;
import com.realdolmen.togethair.domain.User;
import com.realdolmen.togethair.repositories.RolesRepository;
import com.realdolmen.togethair.repositories.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class LoginService implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(LoginService.class);

    @Inject
    private UserRepository userRepository;

    @Inject
    private RolesRepository rolesRepository;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String hashedPassword;
    private boolean isAdmin;
    private Boolean rememberMe;

    public LoginService() {
        if(SecurityUtils.getSubject().isAuthenticated()){

        }


    }

    /**
     * Try and authenticate the user
     */
    public void doLogin() {
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(getUsername(), getPassword(), getRememberMe());


        try {

            subject.login(token);

            if (subject.hasRole("admin")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("admin/index.xhtml");
            } else if (subject.hasRole("partner")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("partner/index.xhtml");
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            }
        } catch (UnknownAccountException ex) {
            facesError("Unknown account");
            log.error(ex.getMessage(), ex);
        } catch (IncorrectCredentialsException ex) {
            facesError("Wrong password");
            log.error(ex.getMessage(), ex);
        } catch (LockedAccountException ex) {
            facesError("Locked account");
            log.error(ex.getMessage(), ex);
        } catch (AuthenticationException | IOException ex) {
            facesError("Unknown error: " + ex.getMessage());
            log.error(ex.getMessage(), ex);
        } finally {


            token.clear();
        }

    }

    private void generatePassword(User user, String plainTextPassword) {
//        RandomNumberGenerator rng = new SecureRandomNumberGenerator();
//        Object salt = rng.nextBytes();
        // Now hash the plain-text password with the random salt and multiple
        // iterations and then Base64-encode the value (requires less space than
        // Hex):

        if (plainTextPassword.length() > 5) {
            setHashedPassword(new Sha256Hash(plainTextPassword).toBase64());

            user.setPassword(hashedPassword);
//        user.setSalt(salt.toString());
        } else throw new IncorrectCredentialsException();

    }


    public void register() {
        User user = new User();

        user.setEmail(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);


        try {
            generatePassword(user, password);

            System.err.println("User with email:" + user.getEmail()
                    + " hashedPassword:" + user.getPassword() + " salt:"
                    + user.getSalt());

            if (isAdmin) {
                Role role = new Role();
                role.setEmail(username);
                role.setRoleName("admin");
                rolesRepository.create(role);
            }

            userRepository.create(user);


            setRememberMe(false);

            doLogin();

            setUsername(SecurityUtils.getSubject().getPrincipal().toString());
            setFirstName(userRepository.getFirstNameofCurrentUser(username));
            setLastName(userRepository.getLastNameofCurrentUser(username));



        } catch (IncorrectCredentialsException ex) {
            facesError("password needs to be at least 5 characters long");
        }


    }

    public void logout() {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (AuthenticationException | IOException ex) {
            facesError("Unknown error: " + ex.getMessage());
            log.error(ex.getMessage(), ex);
        }
    }

    /**
     * Adds a new SEVERITY_ERROR FacesMessage for the ui
     *
     * @param message Error Message
     */
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String senha) {
        this.password = senha;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean lembrar) {
        this.rememberMe = lembrar;
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

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
