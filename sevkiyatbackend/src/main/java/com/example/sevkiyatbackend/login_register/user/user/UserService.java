package com.example.sevkiyatbackend.login_register.user.user;

import com.example.sevkiyatbackend.login_register.user.config.CurrentUser;
import com.example.sevkiyatbackend.login_register.user.dto.PasswordUpdate;
import com.example.sevkiyatbackend.login_register.user.dto.UserUpdate;
import com.example.sevkiyatbackend.login_register.user.dto.PasswordResetRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {
    void save(User user);
    void activateUser(String token);
    Page<User> getUsers(Pageable page, CurrentUser currentUser);
    User getUser(long id);
    User findByEmail(String email);
    User updateUser(long id, UserUpdate userUpdate);
    void deleteUser(long id);
    void handleResetRequest(PasswordResetRequest passwordResetRequest);
    void updatePassword(String token, PasswordUpdate passwordUpdate);
}
