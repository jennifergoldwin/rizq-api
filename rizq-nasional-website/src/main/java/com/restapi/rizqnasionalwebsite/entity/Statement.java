package com.restapi.rizqnasionalwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statement {
    private String id;
    private String userIdentityNumber;
    private String date;
    private String dateWithdrawl;
    private String planId;
    private int amount;
    private String statusPlan;
    private String statusWithdrawal;
}