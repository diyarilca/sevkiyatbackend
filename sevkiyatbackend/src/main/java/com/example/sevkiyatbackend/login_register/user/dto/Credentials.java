package com.example.sevkiyatbackend.login_register.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record Credentials(@Email  String email,@NotBlank String password) {
}
