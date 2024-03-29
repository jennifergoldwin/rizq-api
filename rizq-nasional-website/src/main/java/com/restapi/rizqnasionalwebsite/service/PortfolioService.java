package com.restapi.rizqnasionalwebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.rizqnasionalwebsite.entity.InvestmentGrowth;
import com.restapi.rizqnasionalwebsite.entity.Portfolio;
import com.restapi.rizqnasionalwebsite.mapper.PortfolioMapper;

@Service
public class PortfolioService {
    @Autowired
	private PortfolioMapper portfolioMapper;

    public Portfolio getPortfolioByIdentityNumber(String identityNumber) {
        return portfolioMapper.getUserPortfolio(identityNumber).orElse(null);
    }
    
    public List<InvestmentGrowth> getInvestmentGrowthsByIdentityNumber(String identityNumber){
        return portfolioMapper.getUserInvestmentGrowths(identityNumber);
    }
}
