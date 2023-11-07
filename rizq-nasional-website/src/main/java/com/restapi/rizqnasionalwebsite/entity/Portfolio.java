package com.restapi.rizqnasionalwebsite.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Portfolio {
    private String user_identity;
    private String user_fullname;
    private int total_investment;
    private BigDecimal total_deposit;
    private BigDecimal total_profit;
}
