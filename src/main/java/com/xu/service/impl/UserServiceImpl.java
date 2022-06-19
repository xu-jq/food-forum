package com.xu.service.impl;

import com.xu.dao.UserDao;
import com.xu.dao.impl.UserDaoImpl;
import com.xu.pojo.User;
import com.xu.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    //注册用户
    @Override
    public boolean regist(User user) {
        //1.根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        //判断是否为null
        if (u != null){
            return false;
        }
        //2.保存用户信息
        userDao.save(user);
        return true;
    }

    //登录
    @Override
    public User login(User user) {
        return userDao.findByUsernameandpassword(user.getUsername(), user.getPassword());
    }

    @Override
    public User email(User user) {
        return userDao.findEmail(user.getEmail());
    }

    @Override
    public boolean setPassword(User user) {
        userDao.changePassword(user.getPassword(),user.getEmail());
        return true;
    }

    @Override
    public List recommend() {
        return userDao.getAll();
    }
}
