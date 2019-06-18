package com.example.cinema.data.management;

import java.util.List;

import com.example.cinema.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author sky
 * @date 2019/6/6
 */

 @Mapper
 public interface UserMapper{
     /**
     * 根据level权限添加一个新管理员/员工
     * @param username
     * @param password
     * @param level
     * @return userId
     */
     int addUser(@Param("username") String username, @Param("password") String password,@Param("level") Integer level);

     /**
      * 根据level返回所有人员信息
      * @return
      */
     List<User> selectAllByLevel(@Param("level") Integer level);

     /**
      * 删除user
      * @param username
      * @return
      */
     int deleteUser(@Param("username") String username); 
     
     /**
      * 更新User
      * @param username
      * @param password
      * @param level
      * @return
      */
     int updateUser(@Param("username") String username, @Param("password") String password,@Param("level") Integer level);

 }