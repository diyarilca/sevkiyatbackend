package com.example.sevkiyatbackend.login_register.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@EnableWebMvc
public class StaticResourceConfig implements WebMvcConfigurer {
    @Autowired
    HoaxifyProperties hoaxifyProperties;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = Paths.get(hoaxifyProperties.getStorage().getRoot()).toAbsolutePath().toString() + "/";
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("file:" + path);
    }
    @Bean
    CommandLineRunner createStorageDirectory(){
        return args -> {
            createFolder(Paths.get(hoaxifyProperties.getStorage().getRoot()));
            createFolder(Paths.get(hoaxifyProperties.getStorage().getRoot(),hoaxifyProperties.getStorage().getProfile()));
        };
    }
    private void createFolder(Path path){
        File file = path.toFile();
        boolean isFolderExist = file.exists() && file.isDirectory();
        if(!isFolderExist){
            file.mkdir();
        }
    }
}
