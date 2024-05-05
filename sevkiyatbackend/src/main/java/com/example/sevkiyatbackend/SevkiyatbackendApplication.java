package com.example.sevkiyatbackend;

import com.example.sevkiyatbackend.login_register.user.user.User;
import com.example.sevkiyatbackend.login_register.user.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SevkiyatbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SevkiyatbackendApplication.class, args);
	}
	@Bean
	@Profile("dev")
	CommandLineRunner userCreater(UserRepository userRepository,PasswordEncoder passwordEncoder) {
			return(args)->{
				var userInDB = userRepository.findByEmail("user1@gmail.com");
				if(userInDB != null) return;
					for (var i = 1; i <= 25; i++) {
						User user = new User();
						user.setUsername("user" + i);
						user.setEmail("user" + i + "@gmail.com");
						user.setPassword(passwordEncoder.encode("P4ssword"));
						user.setActive(true);
						userRepository.save(user);
					}
				};
		}
	}
