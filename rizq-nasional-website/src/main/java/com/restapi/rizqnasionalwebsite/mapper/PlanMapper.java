package com.restapi.rizqnasionalwebsite.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.restapi.rizqnasionalwebsite.entity.Plan;

@Mapper
public interface PlanMapper {
    //plan
    @Select("SELECT * FROM plan WHERE id = #{id}")
    Optional<Plan> getAllPlanById(String id);

    @Select("SELECT * FROM plan")
    List<Plan> getAllPlan();

    @Insert("INSERT INTO plan(id, planType, interest, tenure, price) VALUES(#{id}, #{planType}, #{interest}, #{tenure}, #{price})")
    void save(Plan plan);

    @Delete("DELETE FROM plan WHERE id = #{id}")
    void delete(Plan plan);
    
}
