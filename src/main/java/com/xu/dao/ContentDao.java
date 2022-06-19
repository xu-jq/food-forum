package com.xu.dao;

import com.xu.pojo.Content;

import java.util.List;

public interface ContentDao {

    void save(String username, String title, String text,String createtime, String readNum);

    public int findTotalCount(String rname);

    public List<Content> findByPage(int start,int pageSize,String rname);

    Content findPostValue(String id);

    int findTotalCount1(String username);

    List<Content> findByPage1(int start, int pageSize, String username);
}
