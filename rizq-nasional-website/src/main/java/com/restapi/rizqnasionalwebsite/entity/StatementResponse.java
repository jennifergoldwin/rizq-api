package com.restapi.rizqnasionalwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StatementResponse extends Statement{
    private String userName;

    public StatementResponse(Statement statement, String userName) {
        super(statement.getId(), statement.getDate(), statement.getUserIdentityNumber(),
                statement.getProduct(), statement.getLeverage(), statement.getProfitLoss());
        this.userName = userName;
    }
   
}
