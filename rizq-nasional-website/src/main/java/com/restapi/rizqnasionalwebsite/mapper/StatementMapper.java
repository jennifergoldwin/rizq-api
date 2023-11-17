package com.restapi.rizqnasionalwebsite.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.restapi.rizqnasionalwebsite.entity.Plan;
import com.restapi.rizqnasionalwebsite.entity.Statement;
import com.restapi.rizqnasionalwebsite.entity.StatementResponse;
import com.restapi.rizqnasionalwebsite.entity.StockHolding;

@Mapper
public interface StatementMapper {
    @Select("SELECT " + 
        "it.id, " + 
        "DATE_FORMAT(it.date, '%Y-%m-%d') AS date, " +
        "DATE_FORMAT(DATE_ADD(it.date, INTERVAL CAST(p.tenure AS SIGNED) DAY), '%Y-%m-%d') as endDate, " +
        "it.dateWithdrawl, " +
        "p.planType, " + 
        "p.tenure, " + 
        "p.interest, " +
        "it.amount, " +
        "it.statusPlan AS statusPlan, "+
        "it.statusWithdrawal "+
        "FROM investment it JOIN plan p ON it.planId = p.id " +
        "WHERE it.userIdentityNumber = #{identityNumber} ")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "date", column = "date"),
        @Result(property = "endDate", column = "endDate"),
        @Result(property = "dateWithdrawl", column = "dateWithdrawl"),
        @Result(property = "planType", column = "planType"),
        @Result(property = "tenure", column = "tenure"),
        @Result(property = "interest", column = "interest"),
        @Result(property = "amount", column = "amount"),
        @Result(property = "statusPlan", column = "statusPlan"),
        @Result(property = "statusWithdrawal", column = "statusWithdrawal"),
    })
    List<StatementResponse> getUserStatement(String identityNumber);


    @Insert("INSERT INTO investment(id, userIdentityNumber, date, dateWithdrawl, planId, amount, statusPlan, statusWithdrawal) VALUES(#{id}, #{userIdentityNumber},#{date}, #{dateWithdrawl}, #{planId}, #{amount}, #{statusPlan}, #{statusWithdrawal})")
    void saveStatement(Statement statement);

    @Select("SELECT * FROM investment")
    List<Statement> getAllStatements();

    @Select("SELECT * FROM plan")
    List<Plan> getAllPlan();


    @Select("SELECT * FROM stockHolding")
    List<StockHolding> getAllHolding();

    @Insert("INSERT INTO stockHolding(id, userIdentityNumber, investmentId, stockId, purchasedPrice, value, purchasedDate) VALUES(#{id}, #{userIdentityNumber},#{investmentId}, #{stockId}, #{purchasedPrice}, #{value}, #{purchasedDate})")
    void saveHolding(StockHolding stockHolding);

    @Update("UPDATE investment SET statusWithdrawal = 'true', dateWithdrawl = CURDATE() WHERE id = #{id}")
    void updateStatusWithdrawal(String id);
}
