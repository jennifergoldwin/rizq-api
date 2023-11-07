package com.restapi.rizqnasionalwebsite.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.restapi.rizqnasionalwebsite.entity.Portfolio;
import com.restapi.rizqnasionalwebsite.entity.StockPurchased;

@Mapper
public interface PortfolioMapper {
    @Select("SELECT " +
            "u.identityNumber AS user_identity, " +
            "u.fullName AS user_fullname, " +
            "COUNT(DISTINCT sh.stockId) AS total_investment, " +
            "SUM(sh.purchasedPrice) AS total_deposit, " +
            "SUM(sh.currPrice - sh.purchasedPrice) AS total_profit " +
            "FROM users u " +
            "LEFT JOIN ( " +
            "   SELECT " +
            "       sh1.userIdentityNumber, " +
            "       sh1.stockId, " +
            "       sh1.purchasedPrice, " +
            "       s.currPrice " +
            "   FROM stockHolding sh1 " +
            "   LEFT JOIN stocks s ON sh1.stockId = s.id " +
            ") sh ON u.identityNumber = sh.userIdentityNumber " +
            "WHERE u.identityNumber = #{identityNumber} "+
            "GROUP BY u.identityNumber, u.fullName")
    @Results({
        @Result(property = "user_identity", column = "user_identity"),
        @Result(property = "user_fullname", column = "user_fullname"),
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
        "GROUP BY sh.stockId, s.stockName" )
    @Results({
        @Result(property = "stockId", column = "stockId"),
        @Result(property = "stockName", column = "stockName"),
        @Result(property = "totalPurchasedPrice", column = "totalPurchasedPrice")
    })
    List<StockPurchased> getUserAssetStock(String identityNumber);

}
