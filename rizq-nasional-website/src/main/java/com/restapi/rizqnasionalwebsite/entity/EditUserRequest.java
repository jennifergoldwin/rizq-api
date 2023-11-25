package com.restapi.rizqnasionalwebsite.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EditUserRequest {
    private String id;
    private String identityNumber;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String password;
}
