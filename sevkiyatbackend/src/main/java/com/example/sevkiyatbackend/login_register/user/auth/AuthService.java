package com.example.sevkiyatbackend.login_register.user.auth;

import com.example.sevkiyatbackend.login_register.user.dto.AuthResponse;
import com.example.sevkiyatbackend.login_register.user.dto.Credentials;

public interface AuthService {
    AuthResponse authenticate(Credentials creds);
    public void logout(String authorizationHeader);
}
