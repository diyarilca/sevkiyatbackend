package com.example.sevkiyatbackend.login_register.user.exceptions;

import org.springframework.context.i18n.LocaleContextHolder;


public class AuthenticationException extends RuntimeException{

    public AuthenticationException() {
        super(Messages.getMessageForLocale("hoaxify.auth.invalid.credentials", LocaleContextHolder.getLocale()));
    }
}
