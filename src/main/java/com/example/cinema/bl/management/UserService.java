package com.example.cinema.bl.management;

import com.example.cinema.vo.UserVO;
import com.example.cinema.vo.UserForm;
import com.example.cinema.vo.ResponseVO;

/**
 * @author sky
 * @date 2019/6/6 10:43 AM
 *  */

public interface UserService{
    /**
     * 搜索全部经理
     */
    ResponseVO searchAllManager();

    /**
     * 搜索全部员工
     */
    ResponseVO searchAllStaff();
}