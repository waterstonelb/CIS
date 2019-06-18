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
     * @return
     */
    ResponseVO searchAllManager();

    /**
     * 搜索全部员工
     * @return
     */
    ResponseVO searchAllStaff();

    /**
     * 增添员工
     * @param userLForm
     * @return
     */
    ResponseVO insertEmployee(UserWithLevelForm userLForm);

    /**
     * 删除员工
     * @param username
     * @return
     */
    ResponseVO deleteEmployee(String username);

    /**
     * 更新员工信息
     * @param userLForm
     * @return
     */
    ResponseVO updateEmployee(UserWithLevelForm userLForm);
}