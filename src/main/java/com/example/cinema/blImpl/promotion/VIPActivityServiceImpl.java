package com.example.cinema.blImpl.promotion;

import com.example.cinema.bl.promotion.VIPActivityService;
import com.example.cinema.bl.promotion.VIPActivityServiceForBl;
import com.example.cinema.po.VIPAtivity;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPActivityForm;
import com.example.cinema.vo.VIPActivityVO;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import com.example.cinema.data.promotion.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class VIPActivityServiceImpl implements VIPActivityService, VIPActivityServiceForBl {

    @Autowired
    VIPActivityMapper vipActivityMapper;

    @Override
    public VIPAtivity getVIPActivity(int cardId){
        try{
            return vipActivityMapper.getCardById(cardId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseVO getCards() {
        try {
            return ResponseVO.buildSuccess(POListToVOList(vipActivityMapper.getCards()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("fail");
        }

    }

    @Override
    public ResponseVO getCardById(int cardId){
        try {
            return ResponseVO.buildSuccess(new VIPActivityVO(vipActivityMapper.getCardById(cardId)));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("查询失败");
        }
    }

    @Override
    public ResponseVO getValidVIPCards() {
        try {
            List<VIPAtivity> vipAtivityList = vipActivityMapper.getCards();
            List<VIPAtivity> validList=new ArrayList<>();
            for (VIPAtivity vip : vipAtivityList) {
                if(vip.getStatus()==1)
                    validList.add(vip);
            }
            return ResponseVO.buildSuccess(POListToVOList(validList));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("获取有效会员卡失败");
        }
    }

    @Override
    public ResponseVO addNewCard(VIPActivityForm vipActivityForm) {
        try {
            VIPAtivity vipAtivity = new VIPAtivity();
            vipAtivity.setCardName(vipActivityForm.getCardName());
            vipAtivity.setCardPrice(vipActivityForm.getCardPrice());
            vipAtivity.setTargetAmount(vipActivityForm.getTargetAmount());
            vipAtivity.setDiscountAmount(vipActivityForm.getDiscountAmount());
            vipAtivity.setDiscount(vipActivityForm.getDiscount());
            vipAtivity.setStatus(1);
            vipActivityMapper.addNewCard(vipAtivity);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("添加新卡失败");
        }
    }

    @Override
    public ResponseVO updataVIPActivity(VIPActivityForm vipActivityForm) {
        try {
            VIPAtivity vipAtivity = new VIPAtivity();
            vipAtivity.setId(vipActivityForm.getId());
            vipAtivity.setCardName(vipActivityForm.getCardName());
            vipAtivity.setCardPrice(vipActivityForm.getCardPrice());
            vipAtivity.setTargetAmount(vipActivityForm.getTargetAmount());
            vipAtivity.setDiscountAmount(vipActivityForm.getDiscountAmount());
            vipAtivity.setDiscount(vipActivityForm.getDiscount());
            vipAtivity.setStatus(1);
            vipActivityMapper.updataVIPActivity(vipAtivity);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("更新会员卡失败");
        }
    }

    @Override
    public ResponseVO changeStatusToInvalid(int id) {
        try {
            vipActivityMapper.changeStatusToInvalid(id);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("更新失败");
        }
    }

    @Override
    public ResponseVO changeStatusToValid(int id) {
        try {
            vipActivityMapper.changeStatusToValid(id);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("更新失败");
        }
    }


    private List<VIPActivityVO> POListToVOList(List<VIPAtivity> POlist) {
        List<VIPActivityVO> VOlist = new ArrayList<>();
        for (VIPAtivity vip : POlist) {
            VOlist.add(new VIPActivityVO(vip));
        }
        return VOlist;
    }

}