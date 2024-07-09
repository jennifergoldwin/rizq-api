package com.restapi.rizqnasionalwebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.rizqnasionalwebsite.entity.DepoWithdrawlRequest;
import com.restapi.rizqnasionalwebsite.entity.HistoryDeposit;
import com.restapi.rizqnasionalwebsite.entity.HistoryStatement;
import com.restapi.rizqnasionalwebsite.entity.HistoryWithdrawal;
import com.restapi.rizqnasionalwebsite.entity.Investment;
import com.restapi.rizqnasionalwebsite.entity.Statement;
import com.restapi.rizqnasionalwebsite.entity.StatementResponse;
import com.restapi.rizqnasionalwebsite.entity.UserICChangeRequest;
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
        statementMapper.addStatement(statement);
        return statement;
    }
    //update statement
    public void updateStatement(Statement statement){
        statementMapper.updateStatement(statement);
    }

    public void deleteStatement(Statement statement){
        statementMapper.deleteStatement(statement);
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
    public void updateICNumberInvestment(UserICChangeRequest icChangeRequest){
        statementMapper.updateICNumberInvestment(icChangeRequest);
    }
    public List<Investment> getAllInvestment() {
        return statementMapper.getAllInvestment();
    }

    public List<Investment> getAllInvestmentUser(String id){
        return statementMapper.getAllInvestmentUser(id);
    }

    public void withdrawal(DepoWithdrawlRequest dw){
        statementMapper.withdrawal(dw);
    }

    public void deposit(Investment investment){
        statementMapper.deposit(investment);
    }

    public void updateDeposit(DepoWithdrawlRequest dw){
        statementMapper.updateDeposit(dw);
    }

    public void addHistoryStatement(HistoryStatement historyStatement){
        statementMapper.addHistoryStatement(historyStatement);
    }

    public void addHistoryDeposit(HistoryDeposit historyDeposit){
        statementMapper.addHistoryDeposit(historyDeposit);
    }

    public void addHistoryWithdrawal(HistoryWithdrawal historyWithdrawal){
        statementMapper.addHistoryWithdrawal(historyWithdrawal);
    }
}
