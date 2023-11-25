package com.restapi.rizqnasionalwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoAdmin {
    private String id;
    private String identityNumber;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String totalDeposit;
    private String totalProfit;
    private String createdby;
    
}
