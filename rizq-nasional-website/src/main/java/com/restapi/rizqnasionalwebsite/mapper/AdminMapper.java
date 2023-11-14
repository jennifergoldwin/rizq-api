package com.restapi.rizqnasionalwebsite.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.restapi.rizqnasionalwebsite.entity.Admin;


@Mapper
public interface AdminMapper {

    @Select("SELECT * FROM admins WHERE username = #{username}")
    Optional<Admin> findByUsername(String username);

    @Select("SELECT * FROM admins WHERE createdby = #{username}")
    Optional<Admin> findByCreatedBy(String username);
    
    @Insert("INSERT INTO admins(fullName, username, password, role, createdby) VALUES(#{fullName}, #{username},#{password}, #{role}, #{createdby})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(Admin admin);
}
