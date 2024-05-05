package com.example.sevkiyatbackend.login_register.user.file;

import com.example.sevkiyatbackend.login_register.user.config.HoaxifyProperties;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{
    @Autowired
    HoaxifyProperties hoaxifyProperties;
    Tika tika = new Tika();
    @Override
    public String saveBase64StringAsFile(String image) {
        String fileName = UUID.randomUUID().toString();
        Path path = getProfileImagePath(fileName);
        try {
            OutputStream outputStream = new FileOutputStream(path.toFile());
            outputStream.write(decodedImage(image));
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String detectType(String value) {
        return tika.detect(decodedImage(value));
    }

    @Override
    public void deleteProfileImage(String image) {
        if(image == null) return;
        Path path = getProfileImagePath(image);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  byte[] decodedImage(String encodedImage){
        return Base64.getDecoder().decode(encodedImage.split(",")[1]);
    }
    private Path getProfileImagePath(String fileName){
       return Paths.get(hoaxifyProperties.getStorage().getRoot(),hoaxifyProperties.getStorage().getProfile(),fileName);
    }
}
