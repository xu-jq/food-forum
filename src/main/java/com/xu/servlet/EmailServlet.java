package com.xu.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xu.pojo.ResultInfo;
import com.xu.pojo.User;
import com.xu.service.UserService;
import com.xu.service.impl.UserServiceImpl;
import com.xu.util.MailUtils;
import com.xu.util.UuidUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmailServlet extends HttpServlet {
    //找回密码 邮箱
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        String email = req.getParameter("email");

        User user = new User();
        user.setEmail(email);

        UserService service = new UserServiceImpl();
        User e = service.email(user);

        ResultInfo info = new ResultInfo();

        if (e == null){
            info.setFlag(false);
            info.setErrorMsg("未找到相应的邮箱!");
        }
        if (e != null){
            String code1 =UuidUtil.getUuid();
            req.getSession().setAttribute("code1",code1);
            info.setFlag(true);
            MailUtils.sendMail(email,code1,"找回密码");
        }
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resp.getOutputStream(),info);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
