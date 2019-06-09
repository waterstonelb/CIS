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

    @GetMapping("/getAll")
    public ResponseVO getCards(){
        return vipActivityService.getCards();
    }

    @GetMapping("/getbyid")
    public ResponseVO getCardById(@RequestParam int cardId){
        return vipActivityService.getCardById(cardId);
    }

    @GetMapping("/getValid")
    public ResponseVO getValidVIPCards(){ return vipActivityService.getValidVIPCards();}

    @PostMapping("/addcard")
    public ResponseVO addNewCard(@RequestBody VIPActivityForm vipActivityForm){
        return vipActivityService.addNewCard(vipActivityForm);
    }

    @PostMapping("/updata")
    public ResponseVO updataVIPActivity(@RequestBody VIPActivityForm vipActivityForm){
        return vipActivityService.updataVIPActivity(vipActivityForm);
    }

    @GetMapping("/invalid/{id}")
    public ResponseVO changeStatusToInvalid(@PathVariable int id){
        return vipActivityService.changeStatusToInvalid(id);
    }
    @GetMapping("/valid/{id}")
    public ResponseVO changeStatusToValid(@PathVariable int id){
        return vipActivityService.changeStatusToValid(id);
    }


}