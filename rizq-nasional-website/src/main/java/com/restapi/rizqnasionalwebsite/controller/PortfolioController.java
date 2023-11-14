package com.restapi.rizqnasionalwebsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.rizqnasionalwebsite.entity.CommonResponse;
import com.restapi.rizqnasionalwebsite.entity.DepositRequest;
import com.restapi.rizqnasionalwebsite.entity.InvestmentGrowth;
import com.restapi.rizqnasionalwebsite.entity.Portfolio;
import com.restapi.rizqnasionalwebsite.entity.PortfolioResponse;
import com.restapi.rizqnasionalwebsite.entity.Stock;
import com.restapi.rizqnasionalwebsite.entity.StockPurchased;
import com.restapi.rizqnasionalwebsite.entity.StockResponse;
import com.restapi.rizqnasionalwebsite.entity.User;
import com.restapi.rizqnasionalwebsite.service.PortfolioService;
import com.restapi.rizqnasionalwebsite.service.StockService;
import com.restapi.rizqnasionalwebsite.service.UserService;

@RestController
@RequestMapping("/api/v1") 
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;
    @Autowired
    private UserService userService;

    @Autowired
    private StockService stockService;

    @GetMapping("/portfolio/{id}")
    public ResponseEntity<?> getPortfolioUser(@PathVariable String id) {
        try {
            User user = userService.getUserByIdentityNumber(id);
            if (user == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new CommonResponse<>(true,"Invalid identity number",null));
            }

            Portfolio portfolio = portfolioService.getPortfolioByIdentityNumber(id);
            List<StockPurchased> stockPurchased = portfolioService.getStockPurchasedByIdentityNumber(id);
            List<InvestmentGrowth> investmentGrowths = portfolioService.getInvestmentGrowthsByIdentityNumber(id);
            // if ( portfolio == null) {
            //     // portfolio = new Portfolio(0,new BigDecimal(0.0),new BigDecimal(0.0));
            //     return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new CommonResponse<>(false,"Portfolio empty",portfolio));
            // }
            // if (stockPurchased.isEmpty() || stockPurchased == null){
            //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CommonResponse<>(false,"Stock purchased empty",new ArrayList<StockPurchased>()));
            // }
            return ResponseEntity.ok(new CommonResponse<>(false, "success", new PortfolioResponse(portfolio, stockPurchased,investmentGrowths)));
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    //stock
    @GetMapping("/all-stocks")
    public ResponseEntity<?> getAllStocks(){
         try {           
            return ResponseEntity.ok(new CommonResponse<>
            (false, "success", stockService.getAllStock()));
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    @PostMapping("/add-stock")
    public ResponseEntity<?> addStock(@RequestBody Stock stock) {
        try {
            // Check if the user with the provided identityNumber already exists
            Stock existingStock = stockService.getStockById(stock.getId());
            if (existingStock != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new CommonResponse<>(true, "Stock id already exists", null));
            }
            stockService.addStock(stock);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new CommonResponse<>(false, "Stock added", stockService.getAllStock()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    @PutMapping("/update-stock")
    public ResponseEntity<?> updateStock(@RequestBody Stock stock) {
        try {
            // Check if the user with the provided identityNumber already exists
            Stock existingStock = stockService.getStockById(stock.getId());
            if (existingStock == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new CommonResponse<>(true, "Stock doesn't exist", null));
            }
            stockService.updateStock(stock);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new CommonResponse<>(false, "Stock updated", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    

}
