package com.example.cinema.blImpl.management.user;

import java.util.ArrayList;
import java.util.List;

import com.example.cinema.bl.management.UserService;
import com.example.cinema.data.management.UserMapper;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.UserForm;
import com.example.cinema.vo.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cinema.po.User;

/**
 * @author sky
 * @date 2019/6/6 11:35 AM
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired 
    private UserMapper uMapper;
    @Override
    public ResponseVO searchAllManager() {
        try {
            List<User> managerPOList = uMapper.selectAllByLevel(1);
            List<UserVO> managerVOList = new ArrayList<UserVO>();
            for (User manager : managerPOList) {
                UserVO u = new UserVO();
                u.setId(manager.getId());
                u.setLevel(manager.getLevel());
                u.setPassword(manager.getPassword());
                u.setUsername(manager.getUsername());
                managerVOList.add(u);
            }
            return ResponseVO.buildSuccess(managerVOList);
        } catch (Exception e) {
            return ResponseVO.buildFailure("搜索管理员失败");
        }
    }

    @Override
    public ResponseVO searchAllStaff() {
        try {
            List<User> staffPOList = uMapper.selectAllByLevel(2);
            List<UserVO> staffVOList = new ArrayList<UserVO>();
            for (User staff : staffPOList) {
                UserVO u = new UserVO();
                u.setId(staff.getId());
                u.setLevel(staff.getLevel());
                u.setPassword(staff.getPassword());
                u.setUsername(staff.getUsername());
                staffVOList.add(u);
            }
            return ResponseVO.buildSuccess(staffVOList);
        } catch (Exception e) {
            return ResponseVO.buildFailure("搜索员工失败");
        }
    }
    
}