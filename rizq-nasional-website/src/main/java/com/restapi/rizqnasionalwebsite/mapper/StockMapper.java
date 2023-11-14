package com.restapi.rizqnasionalwebsite.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.restapi.rizqnasionalwebsite.entity.Stock;

@Mapper
public interface StockMapper {

    @Select("SELECT * FROM stocks")
    List<Stock> getAllStocks();

    @Select("SELECT * FROM stocks WHERE id = #{id}")
    Optional<Stock> findById(String id);

    @Insert("INSERT INTO stocks(id, stockName, currPrice) VALUES(#{id}, #{stockName},#{currPrice})")
    void save(Stock stock);

    @Update("UPDATE stocks SET currPrice = #{currPrice} WHERE id = #{id}")
    void updateCurrPrice(Stock stock);

}
