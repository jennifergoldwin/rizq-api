package com.restapi.rizqnasionalwebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.rizqnasionalwebsite.entity.Stock;
import com.restapi.rizqnasionalwebsite.mapper.StockMapper;

@Service
public class StockService {
     @Autowired
	private StockMapper stockMapper;

    public Stock getStockById(String id) {
        return stockMapper.findById(id).orElse(null);
    }

    public List<Stock> getAllStock(){
        return stockMapper.getAllStocks();
    }

    public List<Stock> addStock(Stock stock) {
        stockMapper.save(stock);
        return stockMapper.getAllStocks();
    }

    public List<Stock> updateStock(Stock stock) {
        stockMapper.updateCurrPrice(stock);
        return stockMapper.getAllStocks();
    }
    
}
