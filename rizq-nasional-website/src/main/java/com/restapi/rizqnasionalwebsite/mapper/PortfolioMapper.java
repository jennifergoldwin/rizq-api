package com.restapi.rizqnasionalwebsite.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.restapi.rizqnasionalwebsite.entity.InvestmentGrowth;
import com.restapi.rizqnasionalwebsite.entity.Portfolio;
import com.restapi.rizqnasionalwebsite.entity.StockPurchased;

@Mapper
public interface PortfolioMapper {
    // @Select("SELECT " +
    //         "COUNT(DISTINCT sh.id) AS total_investment, " +
    //         "SUM(sh.purchasedPrice) AS total_deposit, " +
    //         "SUM((sh.currPrice - sh.purchasedPrice) * sh.value) AS total_profit " +
    //         "FROM users u " +
    //         "LEFT JOIN ( " +
    //         "   SELECT " +
    //         "       sh1.userIdentityNumber, " +
    //         "       sh1.id, " +
    //         "       sh1.purchasedPrice, " +
    //         "       sh1.value, " +
    //         "       s.currPrice, " +
    //         "       it.statusWithdrawal " +
    //         "   FROM stockHolding sh1 " +
    //         "   LEFT JOIN stocks s ON sh1.stockId = s.id " +
    //         "   LEFT JOIN investment it ON sh1.investmentId = it.id AND it.statusWithdrawal = 'false' " +
    //         ") sh ON u.identityNumber = sh.userIdentityNumber " +
    //         "WHERE u.identityNumber = #{identityNumber} " +
    //         "GROUP BY u.identityNumber, u.fullName")
    // @Results({
    //     @Result(property = "total_investment", column = "total_investment"),
    //     @Result(property = "total_deposit", column = "total_deposit"),
    //     @Result(property = "total_profit", column = "total_profit")
    // })
    // Optional<Portfolio> getUserPortfolio(String identityNumber);


    @Select("SELECT " +
    "SUM((s.currPrice - sh.purchasedPrice) * sh.value) AS total_profit, "+
    "COUNT(DISTINCT i.id) AS total_investment, "+
    "SUM(CASE WHEN i.statusWithdrawal = 'false' THEN i.amount ELSE 0 END) AS total_deposit "+
    "FROM investment i "+
    "JOIN "+
    "stockHolding sh ON i.id = sh.investmentId "+
    "JOIN "+
    "stocks s ON sh.stockId = s.id "+
    "WHERE i.userIdentityNumber = #{identityNumber}" )
    @Results({
        @Result(property = "total_investment", column = "total_investment"),
        @Result(property = "total_deposit", column = "total_deposit"),
        @Result(property = "total_profit", column = "total_profit")
    })
    Optional<Portfolio> getUserPortfolio(String identityNumber);

    @Select("SELECT " + 
        "sh.stockId, "+
        "s.stockName, " + 
        "SUM(sh.purchasedPrice) AS totalPurchasedPrice " +
        "FROM stockHolding sh " +
        "LEFT JOIN stocks s ON sh.stockId = s.id " + 
        "WHERE sh.userIdentityNumber = #{identityNumber} "+
        "GROUP BY sh.stockId, s.stockName")
    @Results({
        @Result(property = "stockId", column = "stockId"),
        @Result(property = "stockName", column = "stockName"),
        @Result(property = "totalPurchasedPrice", column = "totalPurchasedPrice")
    })
    List<StockPurchased> getUserAssetStock(String identityNumber);



    @Select("SELECT DATE_FORMAT(sh.purchasedDate, '%Y-%m') AS month, SUM(s.currPrice - sh.purchasedPrice) AS growth FROM users u INNER JOIN stockHolding sh ON u.identityNumber = sh.userIdentityNumber INNER JOIN stocks s ON sh.stockId = s.id WHERE u.identityNumber =  #{identityNumber} GROUP BY Month ORDER BY Month")
    @Results({
        @Result(property = "month", column = "month"),
        @Result(property = "growth", column = "growth"),
    })
    List<InvestmentGrowth> getUserInvestmentGrowths(String identityNumber);

}
