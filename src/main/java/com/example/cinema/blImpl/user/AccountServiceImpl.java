package com.example.cinema.blImpl.user;

import com.example.cinema.bl.user.AccountService;
import com.example.cinema.data.user.AccountMapper;
import com.example.cinema.po.User;
import com.example.cinema.vo.UserForm;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huwen
 * @date 2019/3/23
 */
@Service
public class AccountServiceImpl implements AccountService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public ResponseVO registerAccount(UserForm userForm) {
        try {
            accountMapper.createNewAccount(userForm.getUsername(), userForm.getPassword());
        } catch (Exception e) {
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess();
    }

    @Override
    public UserVO login(UserForm userForm) {//TODO
        User user = accountMapper.getAccountByName(userForm.getUsername());

        if(userForm.getUsername().equals("root")){//TEST
            user.setLevel(0);//admin
        }else if(userForm.getUsername().equals("manager")){
            user.setLevel(1);
        }else if(userForm.getUsername().equals("staff")){
            user.setLevel(2);
        }else{
            user.setLevel(3);//user
        }//TEST END

        if (null == user || !user.getPassword().equals(userForm.getPassword())) {
            return null;
        }

        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setLevel(user.getLevel());
        userVO.setPassword(user.getPassword());
        userVO.setUsername(user.getUsername());
        return userVO;
    }

}
