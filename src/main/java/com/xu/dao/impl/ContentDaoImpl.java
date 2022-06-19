package com.xu.dao.impl;

import com.xu.dao.ContentDao;
import com.xu.pojo.Comment;
import com.xu.pojo.Content;
import com.xu.pojo.User;
import com.xu.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContentDaoImpl implements ContentDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public void save(String username, String title, String text, String createtime, String readNum) {
        //Content content = new Content();
        String sql = "insert into content(username,title,text,createtime,readNum) values(?,?,?,?,?)";
        //2.执行sql
        template.update(sql,username,title,text,createtime,readNum);

    }

    /*@Override
    public Content findValue() {
        String sql = "select * from content";
        List<Content> contentList = template.query(sql, new BeanPropertyRowMapper<Content>(Content.class));

        return null;
    }

    @Override
    public List<Content> findValue2() {
        String sql = "select * from content";
        List<Content> contentList = template.query(sql, new BeanPropertyRowMapper<Content>(Content.class));
        return contentList;
    }*/

    @Override
    public int findTotalCount(String rname) {
        String sql = "select count(*) from content where 1 = 1 ";
        String title = rname;
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if (title != null && title.length() > 0 && (!title.equals("undefined"))){
            sb.append(" and title like ? ");
            params.add("%"+title+"%");
            sb.append(" or text like ? ");
            params.add("%"+title+"%");

        }
        sql = sb.toString();
        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<Content> findByPage(int start, int pageSize,String rname) {

        String sql = "select * from content where 1 = 1 ";
        String title = rname;
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if (title != null && title.length() > 0 && (!title.equals("undefined"))) {
            sb.append(" and title like ? ");
            params.add("%"+title+"%");
            sb.append(" or text like ? ");
            params.add("%"+title+"%");

        }
        sb.append(" limit ? , ? ");
        sql = sb.toString();
        params.add(start);
        params.add(pageSize);

        return template.query(sql,new BeanPropertyRowMapper<Content>(Content.class),params.toArray());
    }

    @Override
    public Content findPostValue(String id) {
        String sql = "select * from content where id = ?";
        String sql1 = "update content set readNum=readNum+1 where id = ?";
        template.update(sql1,id);
        return template.queryForObject(sql, new BeanPropertyRowMapper<Content>(Content.class),id);
    }

    @Override
    public int findTotalCount1(String username) {
        String sql = "select count(*) from content where username = ?";

        return template.queryForObject(sql,Integer.class,username);
    }

    @Override
    public List<Content> findByPage1(int start, int pageSize, String username) {
        String sql = "select * from content where username = ? limit ? , ?";

        return template.query(sql,new BeanPropertyRowMapper<Content>(Content.class),username,start,pageSize);
    }
}
