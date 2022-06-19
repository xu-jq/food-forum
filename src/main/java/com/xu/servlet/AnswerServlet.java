package com.xu.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xu.pojo.Comment;
import com.xu.pojo.Reply;
import com.xu.pojo.ResultInfo;
import com.xu.service.impl.ReplyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AnswerServlet extends HttpServlet {
    //添加回复信息进数据库
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        String username = (String) req.getSession().getAttribute("username");
        String comment_id = req.getParameter("comment_id");
        String content = req.getParameter("content");

        Reply reply = new Reply();
        reply.setUsername(username);
        reply.setComment_id(comment_id);
        reply.setContent(content);

        ReplyServiceImpl service = new ReplyServiceImpl();
        boolean flag = true;
        ResultInfo info = new ResultInfo();
        if (username == null){
            info.setFlag(false);
            info.setErrorMsg("请先登录后再回复!");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            return;
        }if (content == ""){
            info.setFlag(false);
            info.setErrorMsg("请输入回复的内容!");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            return;
        }
        if(flag){
            service.addReply(reply);
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
