package com.xu.service;

import com.xu.pojo.PageBean;
import com.xu.pojo.Reply;


import java.util.List;

public interface ReplyService {
    //回复 入库
    boolean addReply(Reply reply);

    //所有的回复信息
    List findAllValues();

    //个人中心回复分页
    PageBean<Reply> Page(int currentPage, int pageSize, String username);
}
