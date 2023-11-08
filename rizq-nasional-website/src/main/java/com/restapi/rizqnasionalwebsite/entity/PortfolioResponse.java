package com.restapi.rizqnasionalwebsite.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PortfolioResponse {
    private Portfolio portfolio;
    private List<StockPurchased> stockAllocation;
    private List<InvestmentGrowth> investmentGrowth;
}
