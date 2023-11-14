package com.restapi.rizqnasionalwebsite.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositRequest {
    private String userIdentityNumber;
    private String date;
    private String planId;
    private int amount;
    private List<StockDepo> assetsAllocation;
}
