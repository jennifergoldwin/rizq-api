package com.restapi.rizqnasionalwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Investment {
    private String id;
    private String userIdentityNumber;
    private String dateDeposit;
    private String dateWithdrawl;
    private double totalDeposit;
    private double totalProfit;
    private String statusDeposit;
    private String statusWithdrawal;
}
