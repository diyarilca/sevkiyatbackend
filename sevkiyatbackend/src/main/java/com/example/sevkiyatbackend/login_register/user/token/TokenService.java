package com.example.sevkiyatbackend.login_register.user.token;

import com.example.sevkiyatbackend.login_register.user.dto.Credentials;
import com.example.sevkiyatbackend.login_register.user.user.User;

public interface TokenService {
    public Token createToken(User user, Credentials creds);
    public User verifyToken(String authorizationHeader);
    public void logout(String authorizationHeader);
}
