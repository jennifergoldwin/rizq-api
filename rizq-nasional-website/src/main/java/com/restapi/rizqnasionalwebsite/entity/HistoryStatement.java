package com.restapi.rizqnasionalwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryStatement {
    private String id;
    private String statementId;
    private String date;
    private String product;
    private String leverage;
    private String profitLoss;
}
