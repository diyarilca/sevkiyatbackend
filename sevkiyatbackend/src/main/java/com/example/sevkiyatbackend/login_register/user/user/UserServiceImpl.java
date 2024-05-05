package com.example.sevkiyatbackend.login_register.user.user;

import com.example.sevkiyatbackend.login_register.user.config.CurrentUser;
import com.example.sevkiyatbackend.login_register.user.dto.PasswordUpdate;
import com.example.sevkiyatbackend.login_register.user.dto.UserUpdate;
import com.example.sevkiyatbackend.login_register.user.email.EmailService;
import com.example.sevkiyatbackend.login_register.user.dto.PasswordResetRequest;
import com.example.sevkiyatbackend.login_register.user.exceptions.ActivationNotificationException;
import com.example.sevkiyatbackend.login_register.user.exceptions.InvalidTokenException;
import com.example.sevkiyatbackend.login_register.user.exceptions.NotFoundException;
import com.example.sevkiyatbackend.login_register.user.exceptions.NotUniqueEmailException;
import com.example.sevkiyatbackend.login_register.user.file.FileService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    EmailService emailService;
    @Autowired
    FileService fileService;
    @Transactional(rollbackOn = MailException.class)
    public void save(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActivationToken(UUID.randomUUID().toString());
            userRepository.saveAndFlush(user);
            emailService.sendActivationEmail(user.getEmail(), user.getActivationToken());
        } catch (DataIntegrityViolationException ex){
            throw new NotUniqueEmailException();
        } catch (MailException ex) {
            throw new ActivationNotificationException();
        }
    }

    @Override
    public void activateUser(String token) {
        User inDB = userRepository.findByActivationToken(token);
        if(inDB == null){
            throw new InvalidTokenException();
        }
        inDB.setActive(true);
        inDB.setActivationToken(null);
        userRepository.save(inDB);
    }

    @Override
    public Page<User> getUsers(Pageable page, CurrentUser currentUser) {
        if(currentUser == null) {
            return userRepository.findAll(page);
        }
        return userRepository.findByIdNot(currentUser.getId(), page);
    }

    @Override
    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUser(long id, UserUpdate userUpdate) {
        User inDB = getUser(id);
        inDB.setUsername(userUpdate.username());
        if(userUpdate.image() != null){
            String fileName = fileService.saveBase64StringAsFile(userUpdate.image());
            fileService.deleteProfileImage(inDB.getImage());
            inDB.setImage(fileName);
        }
        return userRepository.save(inDB);
    }

    @Override
    public void deleteUser(long id) {
        User inDB = getUser(id);
        if (inDB.getImage() != null) {
            fileService.deleteProfileImage(inDB.getImage());
        }
        userRepository.delete(inDB);
    }
    @Override
    public void handleResetRequest(PasswordResetRequest passwordResetRequest){
            User inDB = findByEmail(passwordResetRequest.email());
            if(inDB == null) throw new NotFoundException(0);
            inDB.setPasswordResetToken(UUID.randomUUID().toString());
            this.userRepository.save(inDB);
            this.emailService.sendPasswordResetEmail(inDB.getEmail(),inDB.getPasswordResetToken());
        }

    @Override
    public void updatePassword(String token, PasswordUpdate passwordUpdate) {
        User inDB = userRepository.findByPasswordResetToken(token);
        if(inDB == null){
            throw new InvalidTokenException();
        }
        inDB.setPasswordResetToken(null);
        inDB.setPassword(passwordEncoder.encode(passwordUpdate.password()));
        inDB.setActive(true);
        inDB.setActivationToken(null);
        userRepository.save(inDB);
    }
}

