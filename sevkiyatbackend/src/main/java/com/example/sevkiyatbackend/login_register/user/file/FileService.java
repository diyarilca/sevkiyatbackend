package com.example.sevkiyatbackend.login_register.user.file;

public interface FileService {
    String saveBase64StringAsFile(String image);

    String detectType(String value);

    void deleteProfileImage(String image);
}
