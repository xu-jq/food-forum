package com.xu.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xu.pojo.ResultInfo;
import com.xu.pojo.User;
import com.xu.service.UserService;
import com.xu.service.impl.UserServiceImpl;
import com.xu.util.Md5Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class CodeServlet extends HttpServlet {
    //更改密码
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        String code = req.getParameter("code");

        String code1 = (String) req.getSession().getAttribute("code1");
        boolean b = code1.equals(code);
        //boolean b=true;
        ResultInfo info = new ResultInfo();

        if (b == false){
            info.setFlag(false);
            info.setErrorMsg("验证码错误!");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            return;
        }else {
            String password = null;
            try {
                password = Md5Util.encodeByMd5(req.getParameter("password"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            String email = req.getParameter("email");
            User user = new User();
            user.setPassword(password);
            user.setEmail(email);

            UserService service = new UserServiceImpl();
            boolean flag = service.setPassword(user);

            if (flag) {
                info.setFlag(true);
            } else {
                info.setFlag(false);
            }
            //将info对象序列为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);

            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
        }


    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req, resp);
    }


}
