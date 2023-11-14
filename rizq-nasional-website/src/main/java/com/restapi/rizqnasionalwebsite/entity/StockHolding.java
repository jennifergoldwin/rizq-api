package com.restapi.rizqnasionalwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockHolding {
    private String id;
    private String userIdentityNumber;
    private String investmentId;
    private String stockId;
    private int purchasedPrice;
    private int value;
    private String purchasedDate;
}
