package com.example.sevkiyatbackend.login_register.user.auth;

import com.example.sevkiyatbackend.login_register.user.dto.AuthResponse;
import com.example.sevkiyatbackend.login_register.user.dto.Credentials;
import com.example.sevkiyatbackend.login_register.user.dto.UserDTO;
import com.example.sevkiyatbackend.login_register.user.exceptions.AuthenticationException;
import com.example.sevkiyatbackend.login_register.user.token.Token;
import com.example.sevkiyatbackend.login_register.user.token.TokenService;
import com.example.sevkiyatbackend.login_register.user.user.User;
import com.example.sevkiyatbackend.login_register.user.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    TokenService tokenService;
    @Override
    public AuthResponse authenticate(Credentials creds) {
        User inDB = userService.findByEmail(creds.email());
        if(inDB == null) throw new AuthenticationException();
        if(!passwordEncoder.matches(creds.password(),inDB.getPassword())) throw new AuthenticationException();

        Token token = tokenService.createToken(inDB,creds);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUser(new UserDTO(inDB));
        return authResponse;
    }

    @Override
    public void logout(String authorizationHeader) {
        tokenService.logout(authorizationHeader);
    }
}
