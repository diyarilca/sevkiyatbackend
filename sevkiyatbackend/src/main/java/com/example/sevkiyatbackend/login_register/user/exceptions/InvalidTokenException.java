package com.example.sevkiyatbackend.login_register.user.exceptions;

import org.springframework.context.i18n.LocaleContextHolder;

public class InvalidTokenException extends RuntimeException{

    public InvalidTokenException(){
        super(Messages.getMessageForLocale("hoaxify.activate.user.invalid.token", LocaleContextHolder.getLocale()));
    }
}
