package com.restapi.rizqnasionalwebsite.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockResponse {
    private List<Stock> stocks;
}
