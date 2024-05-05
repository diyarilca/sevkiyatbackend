package com.example.sevkiyatbackend.login_register.user.token;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token,String> {
}
