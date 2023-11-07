package com.restapi.rizqnasionalwebsite.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.restapi.rizqnasionalwebsite.entity.User;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE identityNumber = #{identityNumber}")
    Optional<User> findByIdentityNumber(String identityNumber);

    @Insert("INSERT INTO users(fullName, identityNumber, phoneNumber, email, state, city, address, postCode, occupation, bankName, bankAccountNumber, bankHolderName, password, role) VALUES(#{fullName}, #{identityNumber},#{phoneNumber}, #{email}, #{state}, #{city}, #{address}, #{postCode}, #{occupation}, #{bankName}, #{bankAccountNumber}, #{bankHolderName}, #{password}, #{role})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(User user);
}
