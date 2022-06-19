package com.xu.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xu.pojo.Content;
import com.xu.pojo.PageBean;
import com.xu.service.impl.ContentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RouteServlet extends HttpServlet {
//分页 发表文章 搜索

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        //1.接收参数
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");
        String rname = req.getParameter("rname");

        //2.处理参数
        int currentPage = 0;//当前页码，如果不传递，默认第一页
        if (currentPageStr != null && currentPageStr.length()>0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }

        int pageSize = 0;//每页显示条数,如果不传递，默认每页显示五条记录
        if (pageSizeStr != null && pageSizeStr.length()>0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 5;
        }

        //3.调用service查询PageBean对象
        ContentServiceImpl service = new ContentServiceImpl();
        PageBean<Content> pb = service.pageQuery(currentPage,pageSize,rname);

        //4.将PageBean对象序列化json，返回
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resp.getOutputStream(),pb);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}