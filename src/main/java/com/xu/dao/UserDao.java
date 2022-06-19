package com.xu.dao;

import com.xu.pojo.User;

import java.util.List;

public interface UserDao {
    //根据用户名查询用户信息
    public User findByUsername(String username);

    //用户保存
    public void save(User user);

    //登录校验账号密码
    User findByUsernameandpassword(String username,String password);

    //找回密码校验邮箱是否存在
    User findEmail(String email);

    //改密码
    void changePassword(String password,String email);

    //推荐用户栏展示
    List getAll();
}
