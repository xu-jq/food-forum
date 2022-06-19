package com.xu.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xu.service.impl.FavoriteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FavoriteFindServlet extends HttpServlet {
    //查找数据库中是否存在相同评论
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        String content_id = req.getParameter("content_id");
        String username = (String) req.getSession().getAttribute("username");

        FavoriteServiceImpl service = new FavoriteServiceImpl();
        Boolean flag = service.find(content_id, username);

        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resp.getOutputStream(),flag);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
