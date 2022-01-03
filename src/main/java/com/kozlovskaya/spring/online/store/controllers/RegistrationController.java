package com.kozlovskaya.spring.online.store.controllers;

import com.kozlovskaya.spring.online.store.converters.UserRegistrationConverter;
import com.kozlovskaya.spring.online.store.dto.UserRegistrationDto;
import com.kozlovskaya.spring.online.store.entities.User;
import com.kozlovskaya.spring.online.store.services.UserService;
import com.kozlovskaya.spring.online.store.validators.UserRegistrationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final UserRegistrationConverter userRegistrationConverter;
    private final UserRegistrationValidator userRegistrationValidator;

    @PostMapping("/registration")
    public void addNewUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        userRegistrationValidator.validate(userRegistrationDto);
        User user = userRegistrationConverter.dtoToEntity(userRegistrationDto);
        userService.createNewUser(user);
    }
}
