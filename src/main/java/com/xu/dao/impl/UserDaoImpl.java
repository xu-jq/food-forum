package com.xu.dao.impl;

import com.xu.dao.UserDao;
import com.xu.pojo.Content;
import com.xu.pojo.User;
import com.xu.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public User findByUsername(String username) {
        User user = null;
        //1.定义sql
        try {
            String sql = "select * from user where username=?";
            //2.执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username);
        } catch (Exception e) {

        }

        return user;
    }

    @Override
    public void save(User user) {
        //1.定义sql
        String sql = "insert into user(username,password,email,telephone) values(?,?,?,?)";
        //2.执行sql
        template.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getTelephone());
    }

    @Override
    public User findByUsernameandpassword(String username,String password) {
        User user = null;
        //1.定义sql
        try {
            String sql = "select * from user where username=? and password=?";
            //2.执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username,password);
        } catch (Exception e) {

        }
        return user;
    }

    @Override
    public User findEmail(String email) {
        User user = null;
        //1.定义sql
        try {
            String sql = "select * from user where email=?";
            //2.执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),email);
        } catch (Exception e) {

        }

        return user;
    }

    @Override
    public void changePassword(String password,String email) {
        String sql = "UPDATE `user` SET `password`=? WHERE (`email`=?)";
        //2.执行sql
        template.update(sql,password,email);

    }

    @Override
    public List getAll() {
        String sql = "select username from user ";

        return template.query(sql,new BeanPropertyRowMapper<User>(User.class));



    }
}
