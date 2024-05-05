package com.example.sevkiyatbackend.login_register.user.user;

import com.example.sevkiyatbackend.login_register.user.config.CurrentUser;
import com.example.sevkiyatbackend.login_register.user.dto.*;
import com.example.sevkiyatbackend.login_register.user.exceptions.GenericMessage;
import com.example.sevkiyatbackend.login_register.user.exceptions.Messages;
import com.example.sevkiyatbackend.login_register.user.token.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    @PostMapping("/api/v1/users")
    GenericMessage createUser(@Valid @RequestBody UserCreateDTO user) {
        userService.save(user.toUser());
        String message = Messages.getMessageForLocale("hoaxify.create.user.success.message", LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }

    @PatchMapping("/api/v1/users/{token}/active")
    GenericMessage activeUser(@PathVariable String token) {
        userService.activateUser(token);
        String message = Messages.getMessageForLocale("hoaxify.activate.user.success.message", LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }

    @GetMapping("/api/v1/users")
    Page<UserDTO> getUsers(Pageable page, @AuthenticationPrincipal CurrentUser currentUser) {
        return userService.getUsers(page, currentUser).map(UserDTO::new);
    }

    @GetMapping("/api/v1/users/{id}")
    UserDTO getUserById(@PathVariable long id) {
        return new UserDTO(userService.getUser(id));
    }

    @PutMapping("/api/v1/users/{id}")
    @PreAuthorize("#id == principal.id")
    UserDTO updateUser(@PathVariable long id, @Valid @RequestBody UserUpdate userUpdate,@AuthenticationPrincipal CurrentUser currentUser) {
        return new UserDTO(userService.updateUser(id, userUpdate));
    }
    @DeleteMapping("/api/v1/users/{id}")
    @PreAuthorize("#id == principal.id")
    GenericMessage deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return new GenericMessage("User is deleted");
    }
    @PostMapping("api/v1/users/password-resest")
    GenericMessage passwordResetRequest(@Valid @RequestBody PasswordResetRequest passwordResetRequest){
        userService.handleResetRequest(passwordResetRequest);
        return new GenericMessage("Check your email address to reset your password");
    }
    @PatchMapping("api/v1/users/{token}/password")
    GenericMessage setPassword(@PathVariable String token,@Valid @RequestBody PasswordUpdate passwordUpdate){
        userService.updatePassword(token,passwordUpdate);
        return new GenericMessage("Password updated successfuly");
    }
}
