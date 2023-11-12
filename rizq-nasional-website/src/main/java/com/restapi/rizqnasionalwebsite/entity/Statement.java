package com.restapi.rizqnasionalwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statement {
    private String date;
    private String code;
    private String tenure;
    private String plan;
    private String endDate;
    private String interest;
    private String amount;
    private String status;
    private String statusWithdrawal;
}