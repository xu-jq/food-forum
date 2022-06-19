package com.xu.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xu.pojo.Content;
import com.xu.service.ContentService;
import com.xu.service.impl.ContentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostServlet extends HttpServlet {
    //文章页面展示
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        String id = req.getParameter("id");

        Content content = new Content();
        content.setId(id);

        ContentService service = new ContentServiceImpl();
        Content c = service.findValue(id);

        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resp.getOutputStream(),c);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
