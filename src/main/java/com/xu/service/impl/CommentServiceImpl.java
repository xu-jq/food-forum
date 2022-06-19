package com.xu.service.impl;

import com.xu.dao.CommentDao;
import com.xu.dao.impl.CommentDaoImpl;
import com.xu.pojo.Comment;
import com.xu.pojo.Content;
import com.xu.pojo.PageBean;
import com.xu.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao = new CommentDaoImpl();

    @Override
    public boolean addComment(Comment comment) {
        commentDao.save(comment.getUsername(),comment.getTopic_id(),comment.getContent());
        return true;
    }

    @Override
    public PageBean<Comment> pageQuery(int topic_id, int currentPage, int pageSize) {
        PageBean<Comment> pb = new PageBean<Comment>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = commentDao.findTotalCount(topic_id);
        pb.setTotalCount(totalCount);

        int start = (currentPage - 1) * pageSize;
        List<Comment> list = commentDao.findByPage(topic_id,start,pageSize);
        pb.setList(list);

        int totalPage= totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) +1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public PageBean<Comment> Page(int currentPage, int pageSize,String username) {
        PageBean<Comment> pb = new PageBean<Comment>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = commentDao.findTotalCount1(username);
        pb.setTotalCount(totalCount);

        int start = (currentPage - 1) * pageSize;
        List<Comment> list = commentDao.findByPage1(start,pageSize,username);
        pb.setList(list);

        int totalPage= totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) +1;
        pb.setTotalPage(totalPage);
        return pb;
    }
}
