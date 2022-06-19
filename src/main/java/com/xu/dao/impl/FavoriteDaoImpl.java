package com.xu.dao.impl;

import com.xu.dao.FavoriteDao;
import com.xu.pojo.Content;
import com.xu.pojo.Favorite;
import com.xu.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class FavoriteDaoImpl implements FavoriteDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favorite find(String content_id, String username) {
        Favorite favorite = null;

        try {
            String sql = "select * from favorite where content_id = ? and username = ?";
            favorite = template.queryForObject(sql,new BeanPropertyRowMapper<Favorite>(Favorite.class),content_id,username);
        } catch (Exception e) {

        }
        return favorite;
    }

    @Override
    public Boolean save(String content_id, String username) {
        String sql = "insert into favorite(content_id,username) values(?,?)";
        template.update(sql,content_id,username);
        return true;
    }

    public List favorite(String username){

        /*String sql = "select content_id from favorite where username = ?";
        List id = template.query(sql,new BeanPropertyRowMapper<Favorite>(Favorite.class),username);

        String sql1 = "select * from content where id = ?";
        Object[] array = id.toArray();
        List content = null;
        for (int i = 0; i < array.length; i++) {
            content = template.query(sql1,new BeanPropertyRowMapper<Content>(Content.class),array[i]);
        }

        return content;*/
        String sql = "select content_id,title from favorite,content where username = ?";
        return template.query(sql,new BeanPropertyRowMapper<Favorite>(Favorite.class),username);
    }
}
