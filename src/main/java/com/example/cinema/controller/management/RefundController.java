package com.example.cinema.controller.management;

import com.example.cinema.bl.management.UserService;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.UserWithLevelForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * create by 李雪松
 * 2019.6.8
 */

@RestController
public class RefundController {
    // @Autowired
    // private UserService uService;

    @RequestMapping(value = "/",method=RequestMethod.GET)
    public ResponseVO getManagers(){
        return null;
    }


}