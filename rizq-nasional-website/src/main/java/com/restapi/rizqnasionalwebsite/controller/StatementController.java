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
import com.restapi.rizqnasionalwebsite.entity.Statement;
import com.restapi.rizqnasionalwebsite.entity.StatementResponse;
import com.restapi.rizqnasionalwebsite.entity.Stock;
import com.restapi.rizqnasionalwebsite.entity.StockDepo;
import com.restapi.rizqnasionalwebsite.entity.StockHolding;
import com.restapi.rizqnasionalwebsite.entity.User;
import com.restapi.rizqnasionalwebsite.service.StatementService;
import com.restapi.rizqnasionalwebsite.service.StockService;
import com.restapi.rizqnasionalwebsite.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class StatementController {

    @Autowired
    private StatementService statementService;
    @Autowired
    private UserService userService;
    @Autowired
    private StockService stockService;

    @GetMapping("/statement/{id}")
    public ResponseEntity<?> getStatementUser(@PathVariable String id) {
      try {
          User user = userService.getUserByIdentityNumber(id);
          if (user == null){
              return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new CommonResponse<>(true,"Invalid identity number",null));
          }
          
          List<StatementResponse> statement = statementService.getStatementByIdentityNumber(id);
          System.out.println(statement);
          return ResponseEntity.ok(new CommonResponse<>(false, "success", statement));
      } catch (Exception e) {
          e.printStackTrace(); 
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
      }
    }

    @GetMapping("/welcome")
    public String welcome() {
    return "Welcome this endpoint is not secure";
    }

    @GetMapping("/plan")
    public ResponseEntity<?> getAllPlaResponseEntity(){
         try {           
            return ResponseEntity.ok(new CommonResponse<>
            (false, "success", statementService.getAllPlan()));
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody DepositRequest depositRequest){
        try {
            // Check if any stocks in db
            List<Stock> listStock = stockService.getAllStock();
            if (listStock.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new CommonResponse<>(true, "Stock is empty", null));
            }

            //add to investment
            int lenInv = statementService.getAllStatements().size()+1;
            String idInv = "INV00" + lenInv;
            statementService.addStatement(new Statement(idInv,depositRequest.getUserIdentityNumber(),depositRequest.getDate(),depositRequest.getDateWithdrawl(),depositRequest.getPlanId(),depositRequest.getAmount(),depositRequest.getStatusPlan(),depositRequest.getStatusWithdrawl()));

            //add to stockHolding
           
            for (int i = 0 ; i < depositRequest.getAssetsAllocation().size() ; i++){
                int lenSH = statementService.getAllHolding().size()+1;
                String idSH = "SH00" + lenSH;
                StockDepo sp = depositRequest.getAssetsAllocation().get(i);
                Stock stock = new Stock();
                for (int j = 0 ; j < listStock.size(); j++){
                    if (listStock.get(j).getId().equals(sp.getStockId())){
                        stock.setId(listStock.get(j).getId());
                        stock.setStockName(listStock.get(j).getStockName());
                        stock.setCurrPrice(listStock.get(j).getCurrPrice());
                        break;
                    }
                }
                statementService.addHolding(new StockHolding(idSH, depositRequest.getUserIdentityNumber(), idInv, sp.getStockId(), stock.getCurrPrice(), sp.getValue(), depositRequest.getDate()));
            }
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new CommonResponse<>(false, "Deposit updated", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    @PutMapping("/withdrawl/{id}")
    public ResponseEntity<?> withdrawl(@PathVariable String id){
         try {
            statementService.updatedStatement(id);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new CommonResponse<>(false, "Withdrawl requested", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }
}
