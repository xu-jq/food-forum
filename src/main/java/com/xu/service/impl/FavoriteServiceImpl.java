package com.xu.service.impl;

import com.xu.dao.FavoriteDao;
import com.xu.dao.impl.FavoriteDaoImpl;
import com.xu.pojo.Favorite;
import com.xu.service.FavoriteService;

import java.util.List;

public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    @Override
    public Boolean find(String content_id, String username) {
        Favorite favorite = favoriteDao.find(content_id,username);
        return favorite == null;
    }

    @Override
    public Boolean save(String content_id, String username) {
        return favoriteDao.save(content_id,username);
    }

    @Override
    public List favorite(String username) {
        return favoriteDao.favorite(username);
    }
}
