package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.vo.VIPCardForm;
import com.example.cinema.vo.vipCoupon;
import com.example.cinema.vo.ResponseVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liying on 2019/4/14.
 */
@RestController()
@RequestMapping("/vip")
public class VIPCardController {
    @Autowired
    VIPService vipService;

    /**
     * 新增会员卡
     * @param userId
     * @param cardId
     * @return
     */
    @GetMapping("/add")
    public ResponseVO addVIP(@RequestParam int userId ,@RequestParam int cardId){
        return vipService.addVIPCard(userId,cardId);
    }
    /**
     * 根据用户Id获取会员卡
     * @param userId
     * @return
     */
    @GetMapping("{userId}/get")
    public ResponseVO getVIP(@PathVariable int userId){
        return vipService.getCardByUserId(userId);
    }

    /**
     * 获取会员卡种类信息
     * @return
     */
    @GetMapping("/getVIPInfo")
    public ResponseVO getVIPInfo(){
        return vipService.getVIPInfo();
    }

    /**
     * 升级会员卡
     * @param vipCardForm
     * @return
     */
    @PostMapping("/upgrade")
    public ResponseVO upgrade(@RequestBody VIPCardForm vipCardForm){
        return vipService.upgradeCard(vipCardForm);
    }

    /**
     * 会员卡充值
     * @param vipCardForm
     * @return
     */
    @PostMapping("/charge")
    public ResponseVO charge(@RequestBody VIPCardForm vipCardForm){
        return vipService.charge(vipCardForm);
    }

    /**
     * 获赠优惠券
     * @param vipCoupon
     * @return
     */
    @PostMapping("/coupon")
    public ResponseVO sendCoupon(@RequestBody vipCoupon vipCoupon){return vipService.sendCoupon(vipCoupon);}


}
