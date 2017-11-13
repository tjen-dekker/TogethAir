package com.realdolmen.togethair;

import com.realdolmen.togethair.domain.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class main {
    public static void main(String[] args) {
        User user= new User();

        RandomNumberGenerator rng = new SecureRandomNumberGenerator();
        Object salt = rng.nextBytes();

        // Now hash the plain-text password with the random salt and multiple
        // iterations and then Base64-encode the value (requires less space than
        // Hex):
        String hashedPasswordBase64 = new Sha256Hash("fgt").toBase64();

        user.setPassword(hashedPasswordBase64);

        System.out.println(hashedPasswordBase64);


    }
}