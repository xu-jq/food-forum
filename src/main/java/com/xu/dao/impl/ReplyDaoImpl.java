package com.xu.dao.impl;

import com.xu.dao.ReplyDao;
import com.xu.pojo.Reply;
import com.xu.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ReplyDaoImpl implements ReplyDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void save(String username, String comment_id, String content) {
        String sql = "insert into reply(username,comment_id,content) values(?,?,?)";
        template.update(sql,username,comment_id,content);

    }

    @Override
    public List findAllValues() {
        String sql = "select * from reply";
        return template.query(sql,new BeanPropertyRowMapper<Reply>(Reply.class));
    }

    @Override
    public int findTotalCount1(String username) {
        String sql = "select count(*) from reply where username = ?";

        return template.queryForObject(sql,Integer.class,username);
    }

    @Override
    public List<Reply> findByPage1(int start, int pageSize, String username) {
        String sql = "select * from reply where username = ? limit ? , ?";

        return template.query(sql,new BeanPropertyRowMapper<Reply>(Reply.class),username,start,pageSize);
    }
}
