package com.restapi.rizqnasionalwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepoWithdrawlRequest {
    private String userIdentityNumber;
    private String totalDeposit;
    private String totalProfit;
}
