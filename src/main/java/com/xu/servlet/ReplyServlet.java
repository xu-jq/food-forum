package com.xu.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xu.pojo.Comment;
import com.xu.pojo.ResultInfo;
import com.xu.service.CommentService;
import com.xu.service.ContentService;
import com.xu.service.impl.CommentServiceImpl;
import com.xu.service.impl.ContentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReplyServlet extends HttpServlet {
    //评论 写入数据库
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        String username = (String) req.getSession().getAttribute("username");
        String topic_id = req.getParameter("id");
        String content = req.getParameter("content");

        Comment comment = new Comment();
        comment.setUsername(username);
        comment.setTopic_id(topic_id);
        comment.setContent(content);
        System.out.println("content:"+content);

        CommentService service = new CommentServiceImpl();
        boolean flag = true;
        ResultInfo info = new ResultInfo();
        if (username == null){
            info.setFlag(false);
            info.setErrorMsg("请先登录后再评论!");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            return;
        }if (content == ""){
            info.setFlag(false);
            info.setErrorMsg("请输入要评论的内容!");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            return;
        }if(flag){
            service.addComment(comment);
            info.setFlag(true);
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
