package com.example.sevkiyatbackend.login_register.user.config;

import com.example.sevkiyatbackend.login_register.user.user.User;
import com.example.sevkiyatbackend.login_register.user.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User inDB = userService.findByEmail(email);
        if(inDB == null){
            throw new UsernameNotFoundException(email + "is not found");
        }
        return new CurrentUser(inDB);
    }
}
