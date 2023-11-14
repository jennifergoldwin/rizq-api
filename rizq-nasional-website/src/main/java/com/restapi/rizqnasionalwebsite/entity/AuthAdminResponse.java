package com.restapi.rizqnasionalwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthAdminResponse {
    private String token;
    private String username;
    private String fullName;
    private String role;
}
