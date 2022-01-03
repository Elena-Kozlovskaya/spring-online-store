package com.kozlovskaya.spring.online.store.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
