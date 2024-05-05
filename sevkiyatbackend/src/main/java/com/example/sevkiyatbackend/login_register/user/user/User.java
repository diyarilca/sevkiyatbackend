package com.example.sevkiyatbackend.login_register.user.user;

import com.example.sevkiyatbackend.login_register.user.token.Token;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "user_name")
    String username;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    @JsonIgnore
    String password;

    @Column(name = "active")
    @JsonIgnore
    boolean active = false;

    @Column(name = "activation_token")
    @JsonIgnore
    String activationToken;

    @Column(name = "image")
    @Lob
    String image;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<Token> tokens;

    String passwordResetToken;

}
