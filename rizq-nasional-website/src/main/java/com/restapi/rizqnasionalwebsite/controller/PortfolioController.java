package com.restapi.rizqnasionalwebsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.rizqnasionalwebsite.entity.AuthRequest;
import com.restapi.rizqnasionalwebsite.entity.JwtResponse;
import com.restapi.rizqnasionalwebsite.entity.Portfolio;
import com.restapi.rizqnasionalwebsite.entity.PortfolioResponse;
import com.restapi.rizqnasionalwebsite.entity.StockPurchased;
import com.restapi.rizqnasionalwebsite.entity.User;
import com.restapi.rizqnasionalwebsite.service.PortfolioService;
import com.restapi.rizqnasionalwebsite.service.UserService;

@RestController
@RequestMapping("/api/v1/portfolio") 
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/welcome")
    public String welcome() { 
        return "Welcome this endpoint is not secure"; 
    } 


    @GetMapping("/{id}")
    public ResponseEntity<?> getPortfolioUser(@PathVariable String id) {
        try {
            Portfolio portfolio = portfolioService.getPortfolioByIdentityNumber(id);
            List<StockPurchased> stockPurchased = portfolioService.getStockPurchasedByIdentityNumber(id);
            
            if ( portfolio == null || stockPurchased.isEmpty() || stockPurchased == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid identity number");
            }
            return ResponseEntity.ok(new PortfolioResponse(portfolio, stockPurchased));
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Getting data failed");
        }
    }
}
