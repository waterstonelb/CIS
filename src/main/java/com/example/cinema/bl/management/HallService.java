package com.example.cinema.bl.management;

import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.HallVO;

/**
 * @author fjj
 * @date 2019/4/12 2:01 PM
 */
public interface HallService {
    /**
     * 搜索所有影厅
     * @return
     */
    ResponseVO searchAllHall();
    /**
     * 添加影厅
     * @param HallVO
     * @return
     */
    ResponseVO addHall(HallVO hall);
    /**
     * 更新影厅
     * @param HallVO
     * @return
     */
    ResponseVO updateHall(HallVO hall);
    /**
     * 删除影厅
     * @param HallVO
     * @return
     */
    ResponseVO delHall(HallVO hall);
}
