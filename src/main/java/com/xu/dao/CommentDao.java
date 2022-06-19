package com.xu.dao;

import com.xu.pojo.Comment;

import java.util.List;

public interface CommentDao {
    void save(String username,String topic_id,String content);

    public int findTotalCount(int topic_id);

    public List<Comment> findByPage(int topic_id, int start, int pageSize);

    public int findTotalCount1(String username);

    public List<Comment> findByPage1(int start, int pageSize,String username);
}
