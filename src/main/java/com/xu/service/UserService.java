package com.xu.service;

import com.xu.pojo.Content;
import com.xu.pojo.User;

import java.util.List;

public interface UserService {

    //注册
    boolean regist(User user);

    //登录
    User login(User user);

    //查有无该邮箱 发送邮件
    User email(User user);

    //设置密码
    boolean setPassword(User user);

    //推荐用户栏显示
    List recommend();
}
