package com.example.cinema.blImpl.promotion.refund;

import com.example.cinema.vo.RefundPolicyVO;

public interface RefundServiceForBl{

    /**
     * 用于返回一个退票策略的VO
     * @return RefundPolicyVO
     */
    RefundPolicyVO getRefundPolicyVO();
}
