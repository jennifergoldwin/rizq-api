package com.restapi.rizqnasionalwebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.rizqnasionalwebsite.entity.Plan;
import com.restapi.rizqnasionalwebsite.mapper.PlanMapper;

@Service
public class PlanService {

    @Autowired
	private PlanMapper planMapper;

    public Plan getPlanById(String id) {
        return planMapper.getAllPlanById(id).orElse(null);
    }

    public List<Plan> getAllPlan(){
        return planMapper.getAllPlan();
    }

    public Plan addPlan(Plan plan) {
        planMapper.save(plan);
        return plan;
    }

    public List<Plan> deletePlan(Plan plan) {
        planMapper.delete(plan);
        return planMapper.getAllPlan();
    }
}
