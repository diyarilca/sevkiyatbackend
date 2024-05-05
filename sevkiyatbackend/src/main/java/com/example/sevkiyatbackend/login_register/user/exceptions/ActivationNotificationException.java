package com.example.sevkiyatbackend.login_register.user.exceptions;


import org.springframework.context.i18n.LocaleContextHolder;

public class ActivationNotificationException extends RuntimeException{
    public ActivationNotificationException(){
        super(Messages.getMessageForLocale("hoaxify.create.user.email.failure", LocaleContextHolder.getLocale()));
    }
}
