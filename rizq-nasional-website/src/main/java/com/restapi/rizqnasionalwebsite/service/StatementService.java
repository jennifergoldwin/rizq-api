package com.restapi.rizqnasionalwebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.rizqnasionalwebsite.entity.Statement;
import com.restapi.rizqnasionalwebsite.entity.StockHolding;
import com.restapi.rizqnasionalwebsite.mapper.StatementMapper;

@Service
public class StatementService {
    @Autowired
	private StatementMapper statementMapper;

    public List<Statement> getStatementByIdentityNumber(String identityNumber) {
        return statementMapper.getUserStatement(identityNumber);
    }

    public void addStatement(Statement statement){
        statementMapper.saveStatement(statement);
    }

    public void updatedStatement(Statement statement){
        statementMapper.updateStatusWithdrawal(statement);
    }

    public List<Statement> getAllStatements() {
        return statementMapper.getAllStatements();
    }

    public List<StockHolding> getAllHolding() {
        return statementMapper.getAllHolding();
    }

    public void addHolding(StockHolding stockHolding){
        statementMapper.saveHolding(stockHolding);
    }
    
}
