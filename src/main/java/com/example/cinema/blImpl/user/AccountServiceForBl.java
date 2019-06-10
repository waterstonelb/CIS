package com.example.cinema.blImpl.user;

import com.example.cinema.po.User;

public interface AccountServiceForBl {

    /**
     * 根据Id获取User
     * @param id
     * @return User
     */
    User getAccountById(int id);
}
