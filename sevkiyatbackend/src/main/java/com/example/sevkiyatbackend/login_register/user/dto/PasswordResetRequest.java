package com.example.sevkiyatbackend.login_register.user.dto;

import jakarta.validation.constraints.Email;

public record PasswordResetRequest(@Email String email) {
}
