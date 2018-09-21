package com.zoco.mapper;

import com.zoco.entity.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    User selectByUsername(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 获取总记录数
     *
     * @return
     */
    long selectCount();

    /**
     * 获取用户列表
     *
     * @param map
     * @return
     */
    List<User> selectPage(Map<String, Integer> map);
}