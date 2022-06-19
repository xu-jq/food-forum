package com.xu.dao;

import com.xu.pojo.Favorite;

import java.util.List;

public interface FavoriteDao {
    Favorite find(String content_id, String username);

    Boolean save(String content_id, String username);

    List favorite(String username);
}
