package com.example.cinema.blImpl.statistics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.cinema.bl.statistics.UserStatisticsService;
import com.example.cinema.data.statistics.UserStatisticsMapper;
import com.example.cinema.po.UserBuyRecord;
import com.example.cinema.po.UserChargeRecord;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.UserBuyRecordVO;
import com.example.cinema.vo.UserChargeRecordVO;

public class UserStatisticsServiceImpl implements UserStatisticsService{
    @Autowired
    private UserStatist<icsMapper statisticsMapper;
	public ResponseVO getUserBuyRecord() {
        try {
            return ResponseVO.buildSuccess(buyRecordList2buyRecordVOList(statisticsMapper.SelectBuyRecord()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
	}

	private List<UserBuyRecordVO> buyRecordList2buyRecordVOList(List<UserBuyRecord> userBuyRecord) {
        List<UserBuyRecordVO> UserBuyRecordVOList = new ArrayList<>();
        for(UserBuyRecord userbuyRecord : userBuyRecord){
        	UserBuyRecordVOList.add(new UserBuyRecordVO(userbuyRecord));
        }
        return UserBuyRecordVOList;
	}

	@Override
	public ResponseVO getUserChargeRecord() {
        try {
            return ResponseVO.buildSuccess(chargeRecordList2buyRecordVOList(statisticsMapper.SelectChargeRecord()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
	}

	private List<UserChargeRecordVO> chargeRecordList2buyRecordVOList(List<UserChargeRecord> userChargeRecord) {
        List<UserChargeRecordVO> UserChargeRecordVOList = new ArrayList<>();
        for(UserChargeRecord userchargeRecord : userChargeRecord){
        	UserChargeRecordVOList.add(new UserChargeRecordVO(userchargeRecord));
        }
        return UserChargeRecordVOList;
	}
}
