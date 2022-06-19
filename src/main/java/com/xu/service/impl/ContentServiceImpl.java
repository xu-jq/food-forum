package com.xu.service.impl;

import com.xu.dao.ContentDao;
import com.xu.dao.impl.ContentDaoImpl;
import com.xu.pojo.Content;
import com.xu.pojo.PageBean;
import com.xu.service.ContentService;

import java.util.List;

public class ContentServiceImpl implements ContentService {
    private ContentDao contentDao = new ContentDaoImpl();
    @Override
    public boolean released(Content content) {
        contentDao.save(content.getUsername(),content.getTitle(),content.getText(),content.getCreatetime(),content.getReadNum());
        return true;
    }

    /*@Override
    public Content getValue() {
        return contentDao.findValue();
    }*/

    public PageBean<Content> pageQuery(int currentPage,int pageSize,String rname){
        PageBean<Content> pb = new PageBean<Content>();

        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = contentDao.findTotalCount(rname);
        pb.setTotalCount(totalCount);

        int start = (currentPage - 1) * pageSize;
        List<Content> list = contentDao.findByPage(start,pageSize,rname);
        pb.setList(list);

        int totalPage= totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) +1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Content findValue(String id) {
        return contentDao.findPostValue(id);
    }

    @Override
    public PageBean<Content> Page(int currentPage, int pageSize, String username) {
        PageBean<Content> pb = new PageBean<Content>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = contentDao.findTotalCount1(username);
        pb.setTotalCount(totalCount);

        int start = (currentPage - 1) * pageSize;
        List<Content> list = contentDao.findByPage1(start,pageSize,username);
        pb.setList(list);

        int totalPage= totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) +1;
        pb.setTotalPage(totalPage);
        return pb;
    }

}
