package com.xu.dao.impl;

import com.xu.dao.CommentDao;
import com.xu.pojo.Comment;
import com.xu.pojo.Content;
import com.xu.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CommentDaoImpl implements CommentDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void save(String username, String topic_id, String content) {
        String sql = "insert into comment(username,topic_id,content) values(?,?,?)";
        template.update(sql,username,topic_id,content);
    }

    @Override
    public int findTotalCount(int topic_id) {
        String sql = "select count(*) from comment where topic_id = ?";

        return template.queryForObject(sql,Integer.class,topic_id);
    }

    @Override
    public List<Comment> findByPage(int topic_id, int start, int pageSize) {
        String sql = "select * from comment where topic_id = ? limit ? , ?";

        return template.query(sql,new BeanPropertyRowMapper<Comment>(Comment.class),topic_id,start,pageSize);
    }

    @Override
    public int findTotalCount1(String username) {
        String sql = "select count(*) from comment where username = ?";

        return template.queryForObject(sql,Integer.class,username);
    }

    @Override
    public List<Comment> findByPage1(int start, int pageSize, String username) {
        String sql = "select * from comment where username = ? limit ? , ?";

        return template.query(sql,new BeanPropertyRowMapper<Comment>(Comment.class),username,start,pageSize);
    }
}
