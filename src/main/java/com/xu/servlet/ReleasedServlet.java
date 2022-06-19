package com.xu.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xu.pojo.Content;
import com.xu.pojo.ResultInfo;
import com.xu.service.ContentService;
import com.xu.service.impl.ContentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReleasedServlet extends HttpServlet {
    //发表文章 存入数据库
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        String user = (String) req.getSession().getAttribute("username");

        String title = req.getParameter("title");

        String text = req.getParameter("text");
        String createtime = req.getParameter("createtime");
        String readNum = 0+"";

        Content content = new Content();
        content.setUsername(user);
        content.setTitle(title);
        content.setText(text);
        content.setCreatetime(createtime);
        content.setReadNum(readNum);

        ContentService service = new ContentServiceImpl();
        boolean flag = true;
        ResultInfo info = new ResultInfo();
        if(user == null){
            info.setFlag(false);
            info.setErrorMsg("请先登录后再发表!");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            return;
        }if (title == "" || text == ""){
            info.setFlag(false);
            info.setErrorMsg("发表内容为空!");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            return;
        }
        if (flag){
            service.released(content);
            info.setFlag(true);
        }



        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


}
