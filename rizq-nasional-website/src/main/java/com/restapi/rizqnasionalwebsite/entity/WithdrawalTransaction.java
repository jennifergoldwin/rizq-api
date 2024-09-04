package com.restapi.rizqnasionalwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalTransaction {
    private String id;
    private String userId;
    private String date;
    private String bankName;
    private String bankAccount;
    private String amount;
    private String status;
}
