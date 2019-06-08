package com.example.cinema.bl.management;

import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.UserWithLevelForm;

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

    /**
     * 增添员工
     */
    ResponseVO insertEmployee(UserWithLevelForm userLForm);

    /**
     * 删除员工
     */
    ResponseVO deleteEmployee(String username);

    /**
     * 更新员工信息
     */
    ResponseVO updateEmployee(UserWithLevelForm userLForm);
}