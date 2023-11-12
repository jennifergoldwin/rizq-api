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
import com.restapi.rizqnasionalwebsite.entity.Statement;
import com.restapi.rizqnasionalwebsite.entity.StatementResponse;
import com.restapi.rizqnasionalwebsite.entity.User;
import com.restapi.rizqnasionalwebsite.service.StatementService;
import com.restapi.rizqnasionalwebsite.service.UserService;

@RestController
@RequestMapping("/api/v1/statement")
public class StatementController {

    @Autowired
    private StatementService statementService;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getStatementUser(@PathVariable String id) {
      try {
          User user = userService.getUserByIdentityNumber(id);
          if (user == null){
              return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new CommonResponse<>(true,"Invalid identity number",null));
          }

          List<Statement> statement = statementService.getStatementByIdentityNumber(id);
          return ResponseEntity.ok(new CommonResponse<>(false, "success", new StatementResponse(statement)));
      } catch (Exception e) {
          e.printStackTrace(); 
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
      }
    }

    @GetMapping("/welcome")
    public String welcome() {
    return "Welcome this endpoint is not secure";
    }
}
