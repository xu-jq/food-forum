package com.xu.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xu.pojo.ResultInfo;
import com.xu.pojo.User;
import com.xu.service.UserService;
import com.xu.service.impl.UserServiceImpl;
import com.xu.util.Md5Util;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    //登录
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        Map<String,String> map = request.getParameterMap();

        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        String password = user.getPassword();
        try {
            String passwordMD5 = Md5Util.encodeByMd5(password);
            user.setPassword(passwordMD5);
        } catch (Exception e) {

        }

        UserService service = new UserServiceImpl();

        User u = service.login(user);

        ResultInfo info = new ResultInfo();

        if (u == null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误!");
        }
        if (u != null){
            request.getSession().setAttribute("user",u);
            request.getSession().setAttribute("username", user.getUsername());
            info.setFlag(true);
        }
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);




    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
    }


}
