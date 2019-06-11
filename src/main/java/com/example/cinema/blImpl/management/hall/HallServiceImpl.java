package com.example.cinema.blImpl.management.hall;

import com.example.cinema.bl.management.HallService;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.data.management.HallMapper;
import com.example.cinema.po.Hall;
import com.example.cinema.vo.HallVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fjj
 * @date 2019/4/12 2:01 PM
 */
@Service
public class HallServiceImpl implements HallService, HallServiceForBl {
    @Autowired
    HallMapper hallMapper;
    @Autowired
    ScheduleServiceForBl scheduleServiceForBl;

    @Override
    public ResponseVO searchAllHall() {
        try {
            return ResponseVO.buildSuccess(hallList2HallVOList(hallMapper.selectAllHall()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public Hall getHallById(int id) {
        try {
            return hallMapper.selectHallById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public ResponseVO addHall(HallVO hallVO) {
        try {
            hallMapper.addNewHall(hallVO2hallPO(hallVO));
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO updateHall(HallVO hallVO) {
        try {
            if (scheduleServiceForBl.judgeScheduleByHallId(hallVO.getId())) {
                hallMapper.updataHall(hallVO2hallPO(hallVO));
                return ResponseVO.buildSuccess();
            }else {
                return ResponseVO.buildFailure("已有此影厅的排片，不可修改或删除");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO delHall(HallVO hallVO) {
        try {
            if (scheduleServiceForBl.judgeScheduleByHallId(hallVO.getId())) {
                hallMapper.deleteHall(hallVO.getId());
                return ResponseVO.buildSuccess();
            }else {
                return ResponseVO.buildFailure("已有此影厅的排片，不可修改或删除");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }


    private List<HallVO> hallList2HallVOList(List<Hall> hallList) {
        List<HallVO> hallVOList = new ArrayList<>();
        for (Hall hall : hallList) {
            hallVOList.add(new HallVO(hall));
        }
        return hallVOList;
    }

    private Hall hallVO2hallPO(HallVO hallvo) {
        Hall hall = new Hall();
        hall.setId(hallvo.getId());
        hall.setColumn(hallvo.getColumn());
        hall.setRow(hallvo.getRow());
        hall.setName(hallvo.getName());
        return hall;
    }
}
