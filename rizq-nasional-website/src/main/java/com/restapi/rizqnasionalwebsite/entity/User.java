package com.restapi.rizqnasionalwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id; 
    private String fullName; 
    private String identityNumber; 
    private String phoneNumber;
    private String email;
    private String state;
    private String city;
    private String address;
    private String postCode;
    private String occupation;
    private String bankName;
    private String bankAccountNumber;
    private String bankHolderName;
    private String password;  
    private String role;
    private String createdby;
    private String remark;
    private String registrationDate;
}


