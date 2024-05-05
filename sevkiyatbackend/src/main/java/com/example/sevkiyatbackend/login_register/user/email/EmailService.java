package com.example.sevkiyatbackend.login_register.user.email;

public interface EmailService {
    void sendActivationEmail(String email,String activationToken);

    void sendPasswordResetEmail(String email,String passwordResetToken);
}
