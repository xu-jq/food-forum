package com.xu.service.impl;


import com.xu.dao.ReplyDao;

import com.xu.dao.impl.ReplyDaoImpl;
import com.xu.pojo.Content;
import com.xu.pojo.PageBean;
import com.xu.pojo.Reply;
import com.xu.service.ReplyService;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {
    private ReplyDao replyDao = new ReplyDaoImpl();

    public boolean addReply(Reply reply) {
        replyDao.save(reply.getUsername(),reply.getComment_id(),reply.getContent());
        return true;
    }

    @Override
    public List findAllValues() {
        return replyDao.findAllValues();

    }

    @Override
    public PageBean<Reply> Page(int currentPage, int pageSize, String username) {
        PageBean<Reply> pb = new PageBean<Reply>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = replyDao.findTotalCount1(username);
        pb.setTotalCount(totalCount);

        int start = (currentPage - 1) * pageSize;
        List<Reply> list = replyDao.findByPage1(start,pageSize,username);
        pb.setList(list);

        int totalPage= totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) +1;
        pb.setTotalPage(totalPage);
        return pb;
    }
}
