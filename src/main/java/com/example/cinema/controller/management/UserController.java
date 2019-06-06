package com.example.cinema.controller.management;

import com.example.cinema.bl.management.UserService;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.UserWithLevelForm;

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
    @RequestMapping(value = "/usermanage/add",method=RequestMethod.POST)
    public ResponseVO addUser(@RequestBody UserWithLevelForm uLevelForm){//TODO
        return uService.insertEmployee(uLevelForm);
    }
    @RequestMapping(value = "/usermanage/modify",method=RequestMethod.POST)
    public ResponseVO updateUser(@RequestBody UserWithLevelForm uLevelForm){//TODO
        return uService.updateEmployee(uLevelForm);
    }
    @RequestMapping(value = "/usermanage/delete",method=RequestMethod.POST)
    public ResponseVO deleteUser(@RequestBody String username){
        return uService.deleteEmployee(username.substring(1, username.length()-1));
    }


}