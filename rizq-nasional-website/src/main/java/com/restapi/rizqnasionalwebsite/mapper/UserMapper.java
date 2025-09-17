package com.restapi.rizqnasionalwebsite.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.restapi.rizqnasionalwebsite.entity.EditUserRequest;
import com.restapi.rizqnasionalwebsite.entity.Receipt;
import com.restapi.rizqnasionalwebsite.entity.ReceiptResponse;
import com.restapi.rizqnasionalwebsite.entity.User;
import com.restapi.rizqnasionalwebsite.entity.UserInfoAdmin;

@Mapper
public interface UserMapper {
    //login
    @Select("SELECT * FROM users WHERE identityNumber = #{identityNumber}")
    Optional<User> findByIdentityNumber(String identityNumber);

    @Select("SELECT " + 
            "u.id," + 
            "u.identityNumber," + 
            "u.fullName, " + 
            "u.email, " + 
            "u.phoneNumber, u.status, u.password, " + 
            "i.totalDeposit, " + 
            "i.totalProfit, " + 
            "u.createdby, u.remark, u.registrationDate " + 
            "FROM " + 
            "users u " + 
            "LEFT JOIN " + 
            "investment i ON u.identityNumber = i.userIdentityNumber " + 
            " JOIN admins a ON a.username = u.createdby "+
            "WHERE a.createdby = #{username} or a.username = #{username}" 
            // +"GROUP BY " + 
            // "u.id, u.identityNumber, u.fullName, u.email, u.phoneNumber, u.createdby"
            )
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "identityNumber", column = "identityNumber"),
        @Result(property = "fullName", column = "fullName"),
        @Result(property = "email", column = "email"),
        @Result(property = "phoneNumber", column = "phoneNumber"),
        @Result(property = "totalDeposit", column = "totalDeposit"),
        @Result(property = "totalProfit", column = "totalProfit"),
        @Result(property = "createdby", column = "createdby"),
        @Result(property = "remark", column = "remark"),
        @Result(property = "registrationDate", column = "registrationDate"),
        @Result(property = "status", column = "status"),
        @Result(property = "password", column = "password")
    })
    List<UserInfoAdmin> findByCreatedByMasterRole(String username);

    @Select("SELECT " + 
            "u.id," + 
            "u.identityNumber," + 
            "u.fullName, " + 
            "u.email, " + 
            "u.phoneNumber, u.status, u.password, " + 
            "i.totalDeposit, " + 
            "i.totalProfit, " + 
            "u.createdby, u.remark, u.registrationDate " + 
            "FROM " + 
            "users u " + 
            "LEFT JOIN " + 
            "investment i ON u.identityNumber = i.userIdentityNumber " + 
            "WHERE u.createdby = #{username} " 
            // +
            // "GROUP BY " + 
            // "u.identityNumber, u.fullName, u.email, u.phoneNumber, u.createdby"
            )
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "identityNumber", column = "identityNumber"),
        @Result(property = "fullName", column = "fullName"),
        @Result(property = "email", column = "email"),
        @Result(property = "phoneNumber", column = "phoneNumber"),
        @Result(property = "totalDeposit", column = "totalDeposit"),
        @Result(property = "totalProfit", column = "totalProfit"),
        @Result(property = "createdby", column = "createdby"),
        @Result(property = "remark", column = "remark"),
        @Result(property = "registrationDate", column = "registrationDate"),
        @Result(property = "status", column = "status"),
        @Result(property = "password", column = "password")
    })
    List<UserInfoAdmin> findByCreatedBy(String username);

    @Insert("INSERT INTO users(fullName, identityNumber, phoneNumber, email, state, city, address, postCode, occupation, bankName, bankAccountNumber, bankHolderName, password, role, createdby, remark, registrationDate,status) VALUES(#{fullName}, #{identityNumber},#{phoneNumber}, #{email}, #{state}, #{city}, #{address}, #{postCode}, #{occupation}, #{bankName}, #{bankAccountNumber}, #{bankHolderName}, #{password}, #{role}, #{createdby}, #{remark}, #{registrationDate}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(User user);

    @Update("UPDATE users SET bankName = #{bankName}, bankAccountNumber = #{bankAccountNumber}, "+
    "bankHolderName = #{bankHolderName} WHERE identityNumber = #{identityNumber}")
    void updateBankDetails(User user);

    @Update("UPDATE users SET state = #{state}, city = #{city}, "+
    "address = #{address}, postCode = #{postCode}, occupation = #{occupation} WHERE identityNumber = #{identityNumber}")
    void updateProfileDetails(User user);

    @Update("UPDATE users SET fullName = #{fullName}, email = #{email}, "+
    "phoneNumber = #{phoneNumber}, password = #{password}, identityNumber = #{identityNumber}, remark = #{remark}, registrationDate = #{registrationDate}, status = #{status} WHERE id = #{id}")
    void updateUser(EditUserRequest user);

    @Delete("DELETE FROM users WHERE identityNumber = #{id}")
    void delete(String id);

    @Insert("INSERT INTO receipt(id, pakejId, userId, uploadedFile, uploadedDate) " +
        "VALUES(#{id}, #{pakejId}, #{userId}, #{uploadedFile}, #{uploadedDate})")
    void addReceipt(Receipt receipt);


    @Select("""
    SELECT 
        r.id,
        p.namaPakej,
        u.fullName,
        r.uploadedFile,
        r.uploadedDate
    FROM receipt r
    LEFT JOIN users u ON u.identityNumber = r.userId
    LEFT JOIN plan p ON p.id = r.pakejId
    """)
    List<ReceiptResponse> getListReceipt();

}
