package com.restapi.rizqnasionalwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
// @SuperBuilder
public class Statement {
    private String id;
    private String userIdentityNumber;
    private String date;
    private String product;
    private String leverage;
    private String profitLoss;

    
}