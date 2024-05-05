package com.example.sevkiyatbackend.login_register.user.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ApiError {
    private int status;

    private String message;

    private String path;

    private long timestamp = new Date().getTime();

    private Map<String, String> validationErrors = null;
}
