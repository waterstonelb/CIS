package com.example.cinema.data.promotion;


import com.example.cinema.po.VIPAtivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface VIPActivityMapper {

    List<VIPAtivity> getCards();
}
