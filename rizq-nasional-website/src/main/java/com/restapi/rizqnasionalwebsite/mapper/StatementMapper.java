package com.restapi.rizqnasionalwebsite.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.restapi.rizqnasionalwebsite.entity.Statement;
import com.restapi.rizqnasionalwebsite.entity.StockHolding;

@Mapper
public interface StatementMapper {
    @Select("SELECT " + 
        "DATE_FORMAT(it.date, '%Y-%m-%d') AS date, " +
        "it.code, " + 
        "it.tenure, " +
        "it.plan, " +
        "CONCAT(it.interest, '%') AS interest, " +
        "CONCAT('RM', it.amount) AS amount, " +
        "it.statusPlan AS status, "+
        "it.statusWithdrawal, "+
        "DATE_FORMAT(DATE_ADD(it.date, INTERVAL CAST(it.tenure AS SIGNED) DAY), '%Y-%m-%d') as endDate " +
        "FROM investment_table it " +
        "WHERE it.userIdentityNumber = #{identityNumber} ")
    @Results({
        @Result(property = "date", column = "date"),
        @Result(property = "code", column = "code"),
        @Result(property = "tenure", column = "tenure"),
        @Result(property = "endDate", column = "endDate"),
        @Result(property = "plan", column = "plan"),
        @Result(property = "interest", column = "interest"),
        @Result(property = "amount", column = "amount"),
        @Result(property = "status", column = "status"),
        @Result(property = "statusWithdrawal", column = "statusWithdrawal"),
    })
    List<Statement> getUserStatement(String identityNumber);


    @Insert("INSERT INTO investment(id, userIdentityNumber, date, dateWithdrawl, planId, amount, statusPlan, statusWithdrawal) VALUES(#{id}, #{userIdentityNumber},#{date}, #{dateWithdrawl}, #{planId}, #{amount}, #{statusPlan}, #{statusWithdrawal})")
    void saveStatement(Statement statement);

    @Select("SELECT * FROM investment")
    List<Statement> getAllStatements();


    @Select("SELECT * FROM stockholding")
    List<StockHolding> getAllHolding();

    @Insert("INSERT INTO stockholding(id, userIdentityNumber, investmentId, stockId, purchasedPrice, value, purchasedDate) VALUES(#{id}, #{userIdentityNumber},#{investmentId}, #{stockId}, #{purchasedPrice}, #{value}, #{purchasedDate})")
    void saveHolding(StockHolding stockHolding);

    @Update("UPDATE investment SET statusWithdrawal = #{statusWithdrawal} WHERE id = #{id}")
    void updateStatusWithdrawal(Statement statement);
}
