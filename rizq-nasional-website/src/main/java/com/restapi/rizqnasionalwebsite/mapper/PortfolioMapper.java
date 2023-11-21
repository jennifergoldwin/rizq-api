package com.restapi.rizqnasionalwebsite.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.restapi.rizqnasionalwebsite.entity.InvestmentGrowth;
import com.restapi.rizqnasionalwebsite.entity.Portfolio;

@Mapper
public interface PortfolioMapper {
    
    @Select("SELECT "+
    // "COUNT(*) AS total_investment, "+
    "i.totalDeposit AS total_deposit, "+
    "i.totalProfit AS total_profit "+
    "FROM "+
    "investment i "+
    "WHERE "+
    "i.userIdentityNumber = #{identityNumber}"
    )
    @Results({
        // @Result(property = "total_investment", column = "total_investment"),
        @Result(property = "total_deposit", column = "total_deposit"),
        @Result(property = "total_profit", column = "total_profit")
    })
    Optional<Portfolio> getUserPortfolio(String identityNumber);

    
    @Select("SELECT DATE_FORMAT(dateDeposit, '%Y-%m') AS month, "+
    "SUM(totalProfit) AS growth "+
    "FROM investment "+
    "WHERE userIdentityNumber  =  #{identityNumber} GROUP BY month ORDER BY month")
    @Results({
        @Result(property = "month", column = "month"),
        @Result(property = "growth", column = "growth"),
    })
    List<InvestmentGrowth> getUserInvestmentGrowths(String identityNumber);

}
