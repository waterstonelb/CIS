package com.example.cinema.data.management;

import com.example.cinema.po.Hall;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fjj
 * @date 2019/4/11 3:46 PM
 */
@Mapper
public interface HallMapper {
    /**
     * 查询所有影厅信息
     * @return
     */
    List<Hall> selectAllHall();

    /**
     * 增加影厅
     * @param hall
     * @return
     */
    int addNewHall(Hall hall);

    /**
     * 更新影厅
     * @param hall
     * @return
     */
    int updataHall(Hall hall);

    /**
     * 删除影厅
     * @param hallId
     * @return
     */
    int deleteHall(int hallId);

    /**
     * 根据id查询影厅
     * @return
     */
    Hall selectHallById(@Param("hallId") int hallId);
}
