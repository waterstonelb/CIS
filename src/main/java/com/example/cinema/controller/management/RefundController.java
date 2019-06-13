package com.example.cinema.controller.management;

import com.example.cinema.bl.promotion.RefundService;
import com.example.cinema.vo.RefundPolicyVO;
import com.example.cinema.vo.ResponseVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * create by 李雪松
 * 2019.6.8
 */

@RestController
public class RefundController {
    @Autowired
    private RefundService rService;

    @RequestMapping(value = "/refund/manage/update",method=RequestMethod.POST)
    public ResponseVO updateRefundPolicy(@RequestBody RefundPolicyVO rForm){
        return rService.update(rForm.getRefund_day(),rForm.getRefund_hour());
    }
    //'/refund/manage/refundpolicy'
    @RequestMapping(value = "/refund/manage/refundpolicy",method=RequestMethod.GET)
    public ResponseVO getRefundPolicy(){
        return rService.getRefundPolicy();
    }

}