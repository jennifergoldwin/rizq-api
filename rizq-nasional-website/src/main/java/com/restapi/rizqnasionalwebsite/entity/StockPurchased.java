package com.restapi.rizqnasionalwebsite.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockPurchased {
    private String stockId;
    private String stockName;
    private BigDecimal totalPurchasedPrice;
}
