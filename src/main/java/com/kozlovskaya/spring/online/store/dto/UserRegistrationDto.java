package com.kozlovskaya.spring.online.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {
    private Long id;
    private String username;
    private String password;
    private String email;
}
