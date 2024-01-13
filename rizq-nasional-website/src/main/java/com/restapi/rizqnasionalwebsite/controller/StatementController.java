package com.restapi.rizqnasionalwebsite.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.rizqnasionalwebsite.entity.Admin;
import com.restapi.rizqnasionalwebsite.entity.CommonResponse;
import com.restapi.rizqnasionalwebsite.entity.DepoWithdrawlRequest;
import com.restapi.rizqnasionalwebsite.entity.Investment;
import com.restapi.rizqnasionalwebsite.entity.Statement;
import com.restapi.rizqnasionalwebsite.entity.StatementResponse;
import com.restapi.rizqnasionalwebsite.entity.User;
import com.restapi.rizqnasionalwebsite.entity.UserICChangeRequest;
import com.restapi.rizqnasionalwebsite.service.AdminService;
import com.restapi.rizqnasionalwebsite.service.StatementService;
import com.restapi.rizqnasionalwebsite.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class StatementController {

    @Autowired
    private StatementService statementService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/statement-user/{id}")
    public ResponseEntity<?> getStatementUser(@PathVariable String id) {
      try {
          User user = userService.getUserByIdentityNumber(id);
          if (user == null){
              return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new CommonResponse<>(true,"Invalid identity number",null));
          }
          
          List<StatementResponse> statement = statementService.getStatementByUser(id);
          return ResponseEntity.ok(new CommonResponse<>(false, "success", statement));
      } catch (Exception e) {
          e.printStackTrace(); 
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
      }
    }

    @GetMapping("/statement-admin/{username}")
    public ResponseEntity<?> getStatementAdmin(@PathVariable String username) {
      try {
          List<StatementResponse> statement = statementService.getStatementByAdmin(username);
          return ResponseEntity.ok(new CommonResponse<>(false, "success", statement));
      } catch (Exception e) {
          e.printStackTrace(); 
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
      }
    }

    @PostMapping("/add-statement")
    public ResponseEntity<?> addStatement(@RequestBody Statement statement){
        try {
            // int lenStatement = statementService.getAllStatement().size()+1;
            String idSt = "ST-" + UUID.randomUUID();
            statement.setId(idSt);
            statementService.addStatement(statement);
            
            StatementResponse sr = new StatementResponse(statement,userService.getUserByIdentityNumber(statement.getUserIdentityNumber()).getFullName());
            

            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new CommonResponse<>(false, "Statement added",sr));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    @PutMapping("/update-statement")
    public ResponseEntity<?> updateStatement(@RequestBody Statement statement){
        try {
            statementService.updateStatement(statement);
            User us = userService.getUserByIdentityNumber(statement.getUserIdentityNumber());
            Admin admin = adminService.getAdminByUsername(us.getCreatedby());
            List<StatementResponse> statementList = statementService.getStatementByAdmin(admin.getUsername());
            return ResponseEntity.status(HttpStatus.OK)
            .body(new CommonResponse<>(false, "Statement updated", statementList));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    @DeleteMapping("/delete-statement")
    public ResponseEntity<?> deleteStatement(@RequestBody Statement statement) {
        try {
            // Check if the user with the provided identityNumber already exists
            statementService.deleteStatement(statement);
            return ResponseEntity.status(HttpStatus.OK)
            .body(new CommonResponse<>(false, "Statement deleted", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    @GetMapping("/investment/{id}")
    public ResponseEntity<?> getAllInvestmentUser(@PathVariable String id){
        try {
          User user = userService.getUserByIdentityNumber(id);
          if (user == null){
              return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new CommonResponse<>(true,"Invalid identity number",null));
          }
          
          List<Investment> statement = statementService.getAllInvestmentUser(id);
          return ResponseEntity.ok(new CommonResponse<>(false, "success", statement));
      } catch (Exception e) {
          e.printStackTrace(); 
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
      }
    }
    // @PostMapping("/deposit")
    // public ResponseEntity<?> deposit(@RequestBody Investment investment){
    //     try {
    //         //add to investment
    //         int lenInv = statementService.getAllInvestment().size()+1;
    //         String idInv = "INV00" + lenInv;
    //         investment.setId(idInv);
    //         statementService.deposit(investment);

    //         return ResponseEntity.status(HttpStatus.CREATED)
    //         .body(new CommonResponse<>(false, "Deposit success", null));

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                 .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
    //     }
    // }

    @PutMapping("/withdrawl")
    public ResponseEntity<?> withdrawl(@RequestBody DepoWithdrawlRequest dw){
         try {
            statementService.withdrawal(dw);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new CommonResponse<>(false, "Withdrawl requested", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    @PutMapping("/deposit")
    public ResponseEntity<?> updateDeposit(@RequestBody DepoWithdrawlRequest dw){
         try {
            statementService.updateDeposit(dw);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new CommonResponse<>(false, "Deposit success", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    @PutMapping("/updateICNumber")
    public ResponseEntity<?> updateICNumber(@RequestBody UserICChangeRequest iChangeRequest){
         try {
            statementService.updateICNumberInvestment(iChangeRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new CommonResponse<>(false, "Updare IC Number success", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }
}
