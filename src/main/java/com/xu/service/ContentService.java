package com.xu.service;

import com.xu.pojo.Content;
import com.xu.pojo.PageBean;

public interface ContentService {

    //发表文章
    boolean released(Content content);

    //分页+渲染+搜索
    public PageBean<Content> pageQuery(int currentPage, int pageSize,String rname);

    //文章页面展示
    Content findValue(String id);

    //个人中心帖子
    PageBean<Content> Page(int currentPage, int pageSize, String username);
}
