package com.example.JWT.terbaru.Service.Jwt;

import com.example.JWT.terbaru.Entity.Jwt.CustomUserDetails;
import com.example.JWT.terbaru.Entity.Jwt.UserInfo;
import com.example.JWT.terbaru.Repositori.UserRepositori;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserRepositori userRepositori;


    // Todo -> Ambil dari Slf4j
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Entering in loadUserByUsername Method...");
        UserInfo user = userRepositori.findByUsername(username);
        if(user == null){
            logger.error("Username not found: " + username);
            throw new UsernameNotFoundException("could not found user..!!");
        }
        logger.info("User Authenticated Successfully..!!!");


        return new CustomUserDetails(user);
    }
}
