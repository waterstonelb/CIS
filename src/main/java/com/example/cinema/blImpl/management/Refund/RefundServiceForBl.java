package com.example.cinema.blImpl.management.Refund;

import com.example.cinema.data.management.RefundMapper;
import com.example.cinema.vo.RefundPolicyVO;
import com.example.cinema.vo.ResponseVO;

public interface RefundServiceForBl{

    /**
     * 用于返回一个退票策略的VO
     * @return RefundPolicyVO
     */
    RefundPolicyVO getRefundPolicyVO();
}
