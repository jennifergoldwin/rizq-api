package com.restapi.rizqnasionalwebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.rizqnasionalwebsite.entity.Investment;
import com.restapi.rizqnasionalwebsite.entity.Statement;
import com.restapi.rizqnasionalwebsite.entity.StatementResponse;
import com.restapi.rizqnasionalwebsite.mapper.StatementMapper;

@Service
public class StatementService {
    @Autowired
	private StatementMapper statementMapper;

    public List<Statement> getAllStatement() {
        return statementMapper.getAllStatement();
    }

    //add statement
    public Statement addStatement(Statement statement){
        return statementMapper.addStatement(statement);
    }
    //update statement
    public void updateStatement(Statement statement){
        statementMapper.updateStatement(statement);
    }

    //get list statement by userIdentityNumber
    public List<StatementResponse> getStatementByUser(String identityNumber) {
        return statementMapper.getListStatementbyUser(identityNumber);
    }

    //get liststatement by user under one admin
    public List<StatementResponse> getStatementByAdmin(String username) {
        return statementMapper.getListStatementbyAdmin(username);
    }

    //--------------INVESTMENT------------//
    public List<Investment> getAllInvestment() {
        return statementMapper.getAllInvestment();
    }

    public void withdrawal(String id){
        statementMapper.withdrawal(id);
    }

    public void deposit(Investment investment){
        statementMapper.deposit(investment);
    }
}
