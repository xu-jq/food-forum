package com.xu.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xu.service.impl.FavoriteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FavoriteOutServlet extends HttpServlet {
    //将收藏内容写回个人中心
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        String username = (String) req.getSession().getAttribute("username");
        if (username == null){
            return;
        }

        FavoriteServiceImpl service = new FavoriteServiceImpl();
        List content = service.favorite(username);

        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resp.getOutputStream(),content);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
