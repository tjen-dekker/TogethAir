package com.realdolmen.togethair.services;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Min;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class LoginService implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(LoginService.class);

    private String username;
    private String password;
    private Boolean rememberMe;

    public LoginService() {
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
            }

            else if(subject.hasRole("partner")){
                FacesContext.getCurrentInstance().getExternalContext().redirect("partner/index.xhtml");
            }
            else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            }
        }
        catch (UnknownAccountException ex) {
            facesError("Unknown account");
            log.error(ex.getMessage(), ex);
        }
        catch (IncorrectCredentialsException ex) {
            facesError("Wrong password");
            log.error(ex.getMessage(), ex);
        }
        catch (LockedAccountException ex) {
            facesError("Locked account");
            log.error(ex.getMessage(), ex);
        }
        catch (AuthenticationException | IOException ex) {
            facesError("Unknown error: " + ex.getMessage());
            log.error(ex.getMessage(), ex);
        }
        finally {
            token.clear();
        }
    }

    public void logout(){
        try{
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }
        catch (AuthenticationException | IOException ex) {
            facesError("Unknown error: " + ex.getMessage());
            log.error(ex.getMessage(), ex);
        }
    }

    /**
     * Adds a new SEVERITY_ERROR FacesMessage for the ui
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
}
