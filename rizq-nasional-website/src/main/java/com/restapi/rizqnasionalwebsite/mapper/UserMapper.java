package com.restapi.rizqnasionalwebsite.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.restapi.rizqnasionalwebsite.entity.User;
import com.restapi.rizqnasionalwebsite.entity.UserInfoAdmin;

@Mapper
public interface UserMapper {
    //login
    @Select("SELECT * FROM users WHERE identityNumber = #{identityNumber}")
    Optional<User> findByIdentityNumber(String identityNumber);

    @Select("SELECT " + 
            "u.identityNumber," + 
            "u.fullName, " + 
            "u.email, " + 
            "u.phoneNumber, " + 
            "IFNULL(SUM(i.totalDeposit), 0) AS totalDeposit, " + 
            "u.createdby " + 
            "FROM " + 
            "users u " + 
            "LEFT JOIN " + 
            "investment i ON u.identityNumber = i.userIdentityNumber " + 
            " JOIN admins a ON a.username = u.createdby "+
            "WHERE a.createdby = #{username} " +
            "GROUP BY " + 
            "u.identityNumber, u.fullName, u.email, u.phoneNumber, u.createdby")
    @Results({
        @Result(property = "identityNumber", column = "identityNumber"),
        @Result(property = "fullName", column = "fullName"),
        @Result(property = "email", column = "email"),
        @Result(property = "phoneNumber", column = "phoneNumber"),
        @Result(property = "createdby", column = "createdby")
    })
    List<UserInfoAdmin> findByCreatedByMasterRole(String username);

    @Select("SELECT " + 
            "u.identityNumber," + 
            "u.fullName, " + 
            "u.email, " + 
            "u.phoneNumber, " + 
            "IFNULL(SUM(i.totalDeposit), 0) AS totalDeposit, " + 
            "u.createdby " + 
            "FROM " + 
            "users u " + 
            "LEFT JOIN " + 
            "investment i ON u.identityNumber = i.userIdentityNumber " + 
            "WHERE u.createdby = #{username} " +
            "GROUP BY " + 
            "u.identityNumber, u.fullName, u.email, u.phoneNumber, u.createdby")
    @Results({
        @Result(property = "identityNumber", column = "identityNumber"),
        @Result(property = "fullName", column = "fullName"),
        @Result(property = "email", column = "email"),
        @Result(property = "phoneNumber", column = "phoneNumber"),
        @Result(property = "createdby", column = "createdby")
    })
    List<UserInfoAdmin> findByCreatedBy(String username);

    @Insert("INSERT INTO users(fullName, identityNumber, phoneNumber, email, state, city, address, postCode, occupation, bankName, bankAccountNumber, bankHolderName, password, role, createdby) VALUES(#{fullName}, #{identityNumber},#{phoneNumber}, #{email}, #{state}, #{city}, #{address}, #{postCode}, #{occupation}, #{bankName}, #{bankAccountNumber}, #{bankHolderName}, #{password}, #{role}, #{createdby})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(User user);
}
