package com.example.cinema.blImpl.promotion;

import com.example.cinema.bl.promotion.VIPActivityService;
import com.example.cinema.po.VIPAtivity;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPActivityVO;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import com.example.cinema.data.promotion.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class VIPActivityServiceImpl implements VIPActivityService {

    @Autowired
    VIPActivityMapper vipActivityMapper;

    @Override
    public ResponseVO getCards(){
        try{
            return ResponseVO.buildSuccess(POListToVOList(vipActivityMapper.getCards()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("fail");
        }

    }

    private List<VIPActivityVO> POListToVOList(List<VIPAtivity> POlist){
        List<VIPActivityVO> VOlist=new ArrayList<>();
        for (VIPAtivity vip: POlist) {
            VOlist.add(new VIPActivityVO(vip));
        }
        return VOlist;
    }

}