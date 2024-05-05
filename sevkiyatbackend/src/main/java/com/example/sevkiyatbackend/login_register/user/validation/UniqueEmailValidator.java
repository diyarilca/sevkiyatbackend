package com.example.sevkiyatbackend.login_register.user.validation;

import com.example.sevkiyatbackend.login_register.user.user.User;
import com.example.sevkiyatbackend.login_register.user.user.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String> {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        User inDB = userRepository.findByEmail(value);
        return inDB == null;
    }
}
