package com.xu.service;

import java.util.List;

public interface FavoriteService {
    //查重 判断数据库中是否存在同样的收藏
    Boolean find(String content_id, String username);

    //保存收藏信息
    Boolean save(String content_id, String username);

    //将对应收藏写回个人中心
    List favorite(String username);
}
