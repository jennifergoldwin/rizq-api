package com.restapi.rizqnasionalwebsite.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatementResponse {
    private String id;
    private String date;
    private String endDate;
    private String dateWithdrawl;
    private String planType;
    private int tenure;
    private double interest;
    private double amount;
    private String statusPlan;
    private String statusWithdrawal;
}
