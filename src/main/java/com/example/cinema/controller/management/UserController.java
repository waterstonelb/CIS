package com.example.cinema.controller.management;

import com.example.cinema.bl.management.UserService;
import com.example.cinema.vo.ResponseVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * create by 李雪松
 * 2019.6.5
 */

@RestController
public class UserController {
    @Autowired
    private UserService uService;

    @RequestMapping(value = "/usermanage/managers",method=RequestMethod.GET)
    public ResponseVO getManagers(){
        return uService.searchAllManager();
    }
    @RequestMapping(value = "/usermanage/staff",method=RequestMethod.GET)
    public ResponseVO getStaff(){
        return uService.searchAllStaff();
    }

}