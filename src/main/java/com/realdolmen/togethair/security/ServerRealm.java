package com.realdolmen.togethair.security;

import com.realdolmen.togethair.DTO.UserDTO;
import com.realdolmen.togethair.domain.User;
import com.realdolmen.togethair.util.HibernateUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.hibernate.Session;

/**
 * Created by TDKBG57 on 2017-11-09.
 */
public class ServerRealm extends JdbcRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
        final String username = userPassToken.getUsername();

        if (username == null) {
            System.out.println("Username is null.");
            return null;
        }

        // read password hash and salt from db
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            UserDTO userDTO = new UserDTO(session);
            final User user = userDTO.getUserByEmail(username);

            if (user == null) {
                System.out.println("No account found for user [" + username + "]");
                return null;
            }

            // return salted credentials
            SaltedAuthenticationInfo info = new MySaltedAuthentificationInfo(
                    username, user.getPassword(), user.getSalt());

            return info;
        } finally {
            session.getTransaction().commit();
            if (session.isOpen()) session.close();
        }
    }
}
