package com.restapi.rizqnasionalwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.rizqnasionalwebsite.entity.CommonResponse;
import com.restapi.rizqnasionalwebsite.entity.Plan;
import com.restapi.rizqnasionalwebsite.service.PlanService;

@RestController
@RequestMapping("/api/v1") 
public class PlanController {
    @Autowired
    PlanService planService;

    @GetMapping("/all-plan")
    public ResponseEntity<?> getAllPlan(){
         try {           
            return ResponseEntity.ok(new CommonResponse<>
            (false, "success", planService.getAllPlan()));
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    @PostMapping("/add-plan")
    public ResponseEntity<?> addStock(@RequestBody Plan plan) {
        try {
            // Check if the user with the provided identityNumber already exists
            Plan existingPlan = planService.getPlanById(plan.getId());
            if (existingPlan != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new CommonResponse<>(true, "Plan id already exists", null));
            }
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(new CommonResponse<>(false, "Plan added", planService.addPlan(plan)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }

    @DeleteMapping("/delete-plan")
    public ResponseEntity<?> updateStock(@RequestBody Plan plan) {
        try {
            // Check if the user with the provided identityNumber already exists
            Plan existingPlan = planService.getPlanById(plan.getId());
            if (existingPlan == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new CommonResponse<>(true, "Plan doesn't exist", null));
            }
            
            return ResponseEntity.status(HttpStatus.OK)
            .body(new CommonResponse<>(false, "Plan deleted", planService.deletePlan(plan)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(true, e.getLocalizedMessage(), null));
        }
    }
}
