package com.example.cinema.blImpl.statistics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cinema.bl.statistics.UserStatisticsService;
import com.example.cinema.blImpl.promotion.vipservice.VIPServiceForBl;
import com.example.cinema.data.statistics.UserStatisticsMapper;
import com.example.cinema.po.UserBuyRecord;
import com.example.cinema.po.UserChargeRecord;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.UserBuyRecordVO;
import com.example.cinema.vo.UserChargeRecordVO;
/*
 * @author yzh
 * @date 2019/6/18 23:30 PM
 * 实现UserStatisticsService接口
 */
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
    /*
     * 桩
     * 这是返回用户消费记录的桩
	@Override
    public ResponseVO getUserBuyRecord(int id) {
        	List<UserBuyRecord> userbuyrecord = new ArrayList<>();
        	UserBuyRecord userbuyrecord1 = new UserBuyRecord();
        	userbuyrecord1.setName("general");
        	userbuyrecord1.setrealPrice(100.0);
        	userbuyrecord1.settime("2019-6-20 18:00:00");
        	userbuyrecord.add(userbuyrecord1);
            return ResponseVO.buildSuccess(buyRecordList2buyRecordVOList(userbuyrecord));
    }
    */

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
    /*
     * 桩
    @Override
    public ResponseVO getUserChargeRecord(int id) {
        	List<UserChargeRecord> userchargerecord = new ArrayList<>();
        	UserChargeRecord userchargerecord1 = new UserChargeRecord();
        	userchargerecord1.setchargeNum(100.0);
        	userchargerecord1.setUserId(10);
        	userchargerecord1.settime("2019-6-20 18:00:00");
        	userchargerecord.add(userchargerecord1);
        	return ResponseVO.buildSuccess(chargeRecordList2buyRecordVOList(userchargerecord));
    }
    */

    private List<UserChargeRecordVO> chargeRecordList2buyRecordVOList(List<UserChargeRecord> userChargeRecord) {
        List<UserChargeRecordVO> UserChargeRecordVOList = new ArrayList<>();
        for (UserChargeRecord userchargeRecord : userChargeRecord) {
            UserChargeRecordVOList.add(new UserChargeRecordVO(userchargeRecord));
        }
        return UserChargeRecordVOList;
    }
}
