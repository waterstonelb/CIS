package com.example.cinema.blImpl.management.refund;

import com.example.cinema.bl.management.RefundService;
import com.example.cinema.data.management.RefundMapper;
import com.example.cinema.po.RefundPolicy;
import com.example.cinema.vo.RefundPolicyVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefundServiceImpl implements RefundService {

    @Autowired
    RefundMapper rmapper;

    @Override
    public ResponseVO update(Float refund_day, Float refund_hour) {
        try {
            int ret = rmapper.addRefundPolicy(refund_day, refund_hour);
            if(ret==0)return ResponseVO.buildFailure("更新退票策略失败！");
            else return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("更新退票策略失败！");
        }
    }

    @Override
    public ResponseVO getRefundPolicy() {
        try {
            RefundPolicyVO rpv = new RefundPolicyVO();
            RefundPolicy rp = rmapper.getRefundPolicy();
            rpv.setRefund_day(rp.getRefund_day());
            rpv.setRefund_hour(rp.getRefund_hour());
            return ResponseVO.buildSuccess(rpv);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("更新退票策略失败！");
        }
    }
    
}