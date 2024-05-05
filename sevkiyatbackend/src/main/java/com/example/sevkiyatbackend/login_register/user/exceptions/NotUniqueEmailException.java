package com.example.sevkiyatbackend.login_register.user.exceptions;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Collections;
import java.util.Map;

public class NotUniqueEmailException extends RuntimeException{
    public NotUniqueEmailException() {
        super(Messages.getMessageForLocale("hoaxify.error.validation", LocaleContextHolder.getLocale()));
    }

    public Map<String, String> getValidationErrors(){
        return Collections.singletonMap("email", Messages.getMessageForLocale("hoaxify.constraint.email.notunique", LocaleContextHolder.getLocale()));
    }

}
