package com.example.sevkiyatbackend.login_register.user.dto;

import com.example.sevkiyatbackend.login_register.user.token.Token;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    UserDTO user;

    Token token;
}
