package com.restapi.rizqnasionalwebsite.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.restapi.rizqnasionalwebsite.entity.DepoWithdrawlRequest;
import com.restapi.rizqnasionalwebsite.entity.HistoryDeposit;
import com.restapi.rizqnasionalwebsite.entity.HistoryStatement;
import com.restapi.rizqnasionalwebsite.entity.HistoryWithdrawal;
import com.restapi.rizqnasionalwebsite.entity.Investment;
import com.restapi.rizqnasionalwebsite.entity.Statement;
import com.restapi.rizqnasionalwebsite.entity.StatementResponse;
import com.restapi.rizqnasionalwebsite.entity.UserICChangeRequest;

@Mapper
public interface StatementMapper {

    //--------STATEMENT--------//

     //get all statement
    @Select("SELECT * FROM statement")
    List<Statement> getAllStatement();

    //add statement
    @Insert("INSERT INTO statement(id, userIdentityNumber, date, product, "+
    "leverage, profitLoss) VALUES(#{id}, #{userIdentityNumber},#{date}, #{product}, #{leverage}, #{profitLoss})")
    void addStatement(Statement statement); 

    //update statement
    @Update("UPDATE statement SET date = #{date}, product = #{product}, leverage = #{leverage}, "+
    "profitLoss = #{profitLoss} WHERE id = #{id}")
    void updateStatement(Statement statement);

    @Delete("DELETE FROM statement WHERE id = #{id}")
    void deleteStatement(Statement statement);

    @Delete("DELETE FROM statement WHERE userIdentityNumber = #{id}")
    void deleteStatementICNumber(String id);

    //get list statement by userIdentityNumber
    @Select("SELECT s.id, u.fullName as userName, "+
    "s.userIdentityNumber, s.date, s.product, s.leverage, s.profitLoss "+
     "FROM statement s LEFT JOIN users u ON s.userIdentityNumber = u.identityNumber "+
     "WHERE u.identityNumber = #{identityNumber}"
     )
    List<StatementResponse> getListStatementbyUser(String identityNumber);

    //get liststatement by user under one admin
    @Select("SELECT s.id, u.fullName as userName, "+
    "s.userIdentityNumber, s.date, s.product, s.leverage, s.profitLoss "+
     "FROM statement s LEFT JOIN users u ON s.userIdentityNumber = u.identityNumber "+
     " JOIN admins a ON a.username = u.createdby "+
     "WHERE u.createdby = #{username} or a.username = #{username}"
     )
    List<StatementResponse> getListStatementbyAdmin(String username);

    
    //investment
    @Select("SELECT * FROM investment")
    List<Investment> getAllInvestment(); //for generate id

    //get list investment by identity number user
    @Select("SELECT * FROM investment WHERE userIdentityNumber = #{id}")
    List<Investment> getAllInvestmentUser(String id);

    @Insert("INSERT INTO investment(id, userIdentityNumber, dateDeposit, dateWithdrawal, "+
    "totalDeposit, totalProfit, statusDeposit, statusWithdrawal) VALUES(#{id}, #{userIdentityNumber},#{dateDeposit}, #{dateWithdrawal}, "+
    "#{totalDeposit}, #{totalProfit}, #{statusDeposit}, #{statusWithdrawal})")
    void deposit(Investment investment);

    @Update("UPDATE investment SET statusWithdrawal = 'true', " +
    "dateWithdrawal = CONVERT_TZ(NOW(), 'UTC', 'Asia/Kuala_Lumpur'), totalDeposit = totalDeposit - #{totalDeposit}, " +
    "totalProfit = totalProfit - #{totalProfit} " +
    "WHERE userIdentityNumber = #{userIdentityNumber} AND totalDeposit >= #{totalDeposit} " +
    "AND totalProfit >= #{totalProfit}")
    void withdrawal(DepoWithdrawlRequest dw);


    @Update("UPDATE investment SET totalDeposit = #{totalDeposit}, totalProfit = #{totalProfit}, "+
    "dateDeposit = CONVERT_TZ(NOW(), 'UTC', 'Asia/Kuala_Lumpur') WHERE userIdentityNumber = #{userIdentityNumber}")
    void updateDeposit(DepoWithdrawlRequest dw);

    @Delete("DELETE FROM investment WHERE userIdentityNumber = #{id}")
    void deleteInvestment(String id);
    
    @Update("UPDATE investment SET userIdentityNumber = #{icNumberNew} WHERE userIdentityNumber = #{icNumberOld}")
    void updateICNumberInvestment(UserICChangeRequest icNumberReq);

    @Insert("INSERT INTO history_statement(id, statementId, date, product, leverage, profitLoss) VALUES(#{id}, #{statementId}, #{date}, #{product}, #{leverage}, #{profitLoss})")
    void addHistoryStatement(HistoryStatement historyStatement);

    @Insert("INSERT INTO history_deposit(id, userId, date, amount) VALUES(#{id}, #{userId}, #{date}, #{amount})")
    void addHistoryDeposit(HistoryDeposit historyDeposit);

    @Insert("INSERT INTO history_withdrawal(id, userId, date, amount) VALUES(#{id}, #{userId}, #{date}, #{amount})")
    void addHistoryWithdrawal(HistoryWithdrawal historyWithdrawal);

}
