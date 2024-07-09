package com.restapi.rizqnasionalwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryWithdrawal {
    private String id;
    private String userId;
    private String date;
    private String amountDeposit;
    private String amountProfit;
}
