package com.restapi.rizqnasionalwebsite.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.restapi.rizqnasionalwebsite.entity.Statement;

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
}
