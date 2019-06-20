package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.VIPActivityService;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPActivityForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * create by 梁斌
 * 2019.6.5
 */
@RestController()
@RequestMapping("/vipactivity")

public class VIPActivityController{

    @Autowired 
    VIPActivityService vipActivityService;

    /**
     * 获取所有会员卡种类
     * @return
     */
    @GetMapping("/getAll")
    public ResponseVO getCards(){
        return vipActivityService.getCards();
    }

    /**
     * 通过会员卡Id获取会员卡种类
     * @param cardId
     * @return
     */
    @GetMapping("/getbyid")
    public ResponseVO getCardById(@RequestParam int cardId){
        return vipActivityService.getCardById(cardId);
    }

    /**
     * 获取有效卡
     * @return
     */
    @GetMapping("/getValid")
    public ResponseVO getValidVIPCards(){ return vipActivityService.getValidVIPCards();}

    /**
     * 增添卡策略
     * @param vipActivityForm
     * @return
     */
    @PostMapping("/addcard")
    public ResponseVO addNewCard(@RequestBody VIPActivityForm vipActivityForm){
        return vipActivityService.addNewCard(vipActivityForm);
    }

    /**
     * 更新会员卡策略
     * @param vipActivityForm
     * @return
     */
    @PostMapping("/updata")
    public ResponseVO updataVIPActivity(@RequestBody VIPActivityForm vipActivityForm){
        return vipActivityService.updataVIPActivity(vipActivityForm);
    }

    /**
     * 将会员卡策略状态改为无效
     * @param id
     * @return
     */
    @GetMapping("/invalid/{id}")
    public ResponseVO changeStatusToInvalid(@PathVariable int id){
        return vipActivityService.changeStatusToInvalid(id);
    }
    /**
     * 将会员卡策略改为有效
     * @param id
     * @return
     */
    @GetMapping("/valid/{id}")
    public ResponseVO changeStatusToValid(@PathVariable int id){
        return vipActivityService.changeStatusToValid(id);
    }


}