package com.example.sevkiyatbackend.login_register.user.dto;

import com.example.sevkiyatbackend.login_register.user.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    long id;
    String username;
    String email;
    String image;

    public UserDTO(User user){
        setId(user.getId());
        setUsername(user.getUsername());
        setEmail(user.getEmail());
        setImage(user.getImage());
    }

}
