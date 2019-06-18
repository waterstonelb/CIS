package com.example.cinema.blImpl.statistics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cinema.bl.statistics.UserStatisticsService;
import com.example.cinema.blImpl.promotion.vipservice.VIPServiceForBl;
import com.example.cinema.data.statistics.UserStatisticsMapper;
import com.example.cinema.po.AdminUserBuyRecord;
import com.example.cinema.po.UserBuyRecord;
import com.example.cinema.po.UserChargeRecord;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.UserBuyRecordVO;
import com.example.cinema.vo.UserChargeRecordVO;
import com.example.cinema.vo.amountVO;
@Service
public class UserStatisticsServiceImpl implements UserStatisticsService{
    @Autowired
    private UserStatisticsMapper UserstatisticsMapper;
    @Autowired
    VIPServiceForBl vipServiceForBl;
    public ResponseVO getUserBuyRecord(int id) {
        try {
            return ResponseVO.buildSuccess(buyRecordList2buyRecordVOList(UserstatisticsMapper.SelectBuyRecord(id)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    private List<UserBuyRecordVO> buyRecordList2buyRecordVOList(List<UserBuyRecord> userBuyRecord) {
        List<UserBuyRecordVO> UserBuyRecordVOList = new ArrayList<>();
        for (UserBuyRecord userbuyRecord : userBuyRecord) {
            UserBuyRecordVOList.add(new UserBuyRecordVO(userbuyRecord));
        }
        return UserBuyRecordVOList;
    }

    

    @Override
    public ResponseVO getUserChargeRecord(int id) {
        try {
            return ResponseVO.buildSuccess(chargeRecordList2buyRecordVOList(UserstatisticsMapper.SelectChargeRecord(id)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    private List<UserChargeRecordVO> chargeRecordList2buyRecordVOList(List<UserChargeRecord> userChargeRecord) {
        List<UserChargeRecordVO> UserChargeRecordVOList = new ArrayList<>();
        for (UserChargeRecord userchargeRecord : userChargeRecord) {
            UserChargeRecordVOList.add(new UserChargeRecordVO(userchargeRecord));
        }
        return UserChargeRecordVOList;
    }
}
