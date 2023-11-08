package com.restapi.rizqnasionalwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String userIdentityNumber;
    private String userName;
}
