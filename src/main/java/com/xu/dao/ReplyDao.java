package com.xu.dao;

import com.xu.pojo.Reply;

import java.util.List;

public interface ReplyDao {
    void save(String username, String comment_id, String content);

    List findAllValues();

    int findTotalCount1(String username);

    List<Reply> findByPage1(int start, int pageSize, String username);
}
