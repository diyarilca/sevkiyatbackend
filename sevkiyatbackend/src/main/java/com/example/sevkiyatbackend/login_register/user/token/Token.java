package com.example.sevkiyatbackend.login_register.user.token;

import com.example.sevkiyatbackend.login_register.user.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Token {
    @Id
    String token;
    @Transient
    String prefix = "Bearer";

    @JsonIgnore
    @ManyToOne
    User user;
    public Token(String prefix, String token) {
        this.prefix = prefix;
        this.token = token;
    }
    public Token(){}
}
