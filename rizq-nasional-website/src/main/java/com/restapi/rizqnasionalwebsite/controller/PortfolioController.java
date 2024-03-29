package com.restapi.rizqnasionalwebsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.rizqnasionalwebsite.entity.CommonResponse;
import com.restapi.rizqnasionalwebsite.entity.InvestmentGrowth;
import com.restapi.rizqnasionalwebsite.entity.Portfolio;
import com.restapi.rizqnasionalwebsite.entity.PortfolioResponse;
import com.restapi.rizqnasionalwebsite.entity.User;
import com.restapi.rizqnasionalwebsite.service.PortfolioService;
import com.restapi.rizqnasionalwebsite.service.UserService;

@RestController
@RequestMapping("/api/v1") 
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;
    @Autowired
    private UserService userService;

    @GetMapping("/portfolio/{id}")
    public ResponseEntity<?> getPortfolioUser(@PathVariable String id) {
        try {
            User user = userService.getUserByIdentityNumber(id);
            if (user == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new CommonResponse<>(true,"Invalid identity number",null));
            }

            Portfolio portfolio = portfolioService.getPortfolioByIdentityNumber(id);
            List<InvestmentGrowth> investmentGrowths = portfolioService.getInvestmentGrowthsByIdentityNumber(id);
            
            return ResponseEntity.ok(new CommonResponse<>(false, "success", new PortfolioResponse(portfolio, investmentGrowths)));
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

}
