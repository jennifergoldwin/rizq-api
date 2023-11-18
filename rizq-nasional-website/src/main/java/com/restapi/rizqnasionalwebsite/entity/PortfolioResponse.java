package com.restapi.rizqnasionalwebsite.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PortfolioResponse {
    private Portfolio portfolio;
    private List<InvestmentGrowth> investmentGrowth;
}
