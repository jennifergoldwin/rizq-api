package com.restapi.rizqnasionalwebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.rizqnasionalwebsite.entity.Plan;
import com.restapi.rizqnasionalwebsite.entity.Statement;
import com.restapi.rizqnasionalwebsite.entity.StatementResponse;
import com.restapi.rizqnasionalwebsite.entity.StockHolding;
import com.restapi.rizqnasionalwebsite.mapper.StatementMapper;

@Service
public class StatementService {
    @Autowired
	private StatementMapper statementMapper;

    public List<StatementResponse> getStatementByIdentityNumber(String identityNumber) {
        return statementMapper.getUserStatement(identityNumber);
    }

    public void addStatement(Statement statement){
        statementMapper.saveStatement(statement);
    }

    public void updatedStatement(String id){
        statementMapper.updateStatusWithdrawal(id);
    }

    public List<Statement> getAllStatements() {
        return statementMapper.getAllStatements();
    }

    public List<Plan> getAllPlan() {
        return statementMapper.getAllPlan();
    }

    public List<StockHolding> getAllHolding() {
        return statementMapper.getAllHolding();
    }

    public void addHolding(StockHolding stockHolding){
        statementMapper.saveHolding(stockHolding);
    }
    
}
