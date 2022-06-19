package com.xu.service;

import com.xu.pojo.Comment;
import com.xu.pojo.PageBean;

import java.util.List;

public interface CommentService {
    //评论 写入数据库
    boolean addComment(Comment comment);

    //评论分页
    PageBean<Comment> pageQuery(int topic_id, int currentPage, int pageSize);

    //个人中心评论分页
    PageBean<Comment> Page(int currentPage, int pageSize, String username);
}
