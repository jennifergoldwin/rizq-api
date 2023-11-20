package com.restapi.rizqnasionalwebsite.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.restapi.rizqnasionalwebsite.entity.Admin;

@Mapper
public interface AdminMapper {

    @Select("SELECT * FROM admins WHERE username = #{username}")
    Optional<Admin> findByUsername(String username);

    @Select("SELECT * FROM admins WHERE createdby = #{username}")
    List<Admin> findByCreatedBy(String username);
    
    @Insert("INSERT INTO admins(fullName, username, password, role, createdby) VALUES(#{fullName}, #{username},#{password}, #{role}, #{createdby})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(Admin admin);

    @Delete("DELETE FROM admins WHERE username = #{username}")
    void delete(Admin admin);

    @Update("UPDATE admins SET fullName = #{fullName}, username = #{username} "+
    "password = #{password} WHERE id = #{id}")
    void update(Admin admin);
}
