package com.zoco.service.impl;

import com.zoco.swy.util.MD5Util;
import com.zoco.vo.PagedResultEntity;
import com.zoco.entity.User;
import com.zoco.exception.RapidException;
import com.zoco.mapper.UserMapper;
import com.zoco.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户处理实现类
 *
 * @author zoco
 * @creat 2018-09-19-15:53
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User Login(String username, String password) {
        try {
            User user = userMapper.selectByUsername(username);
            if (password != null && !password.equals("") && user != null) {
                password = MD5Util.getMD5(password);
                if (password.equals(user.getPassword())) {
                    // TODO: 2018/9/21 用户消息放入redis
                    return user;
                }
            }
            return null;
        } catch (Exception e) {
            throw new RapidException(e);
        }
    }

    @Override
    public int LoginOut(HttpServletRequest request, HttpServletResponse response) {
        // TODO: 2018/9/21  销毁redis的内容
        return 0;
    }

    @Override
    public int delUser(String user_id) {
        int flag = -1;
        try {
            flag = userMapper.deleteByPrimaryKey(Integer.valueOf(user_id));
            return flag;
        } catch (Exception e) {
            throw new RapidException(e);
        }
    }

    @Override
    public int insertUser(User user) {
        int flag = -1;
        try {
            flag = userMapper.updateByPrimaryKeySelective(user);
            return flag;
        } catch (Exception e) {
            throw new RapidException(e);
        }

    }

    @Override
    public int updateUserMessage(User user) {
        int flag = -1;
        try {
            userMapper.updateByPrimaryKeySelective(user);
            return flag;
        } catch (Exception e) {
            throw new RapidException(e);
        }
    }

    @Override
    public PagedResultEntity<User> selectPage(PagedResultEntity<User> pagedResult) {
        List list;
        try {
            Map<String, Integer> map = new HashMap<>();
            map.put("beginIndex", pagedResult.getStart());
            map.put("endIndex", pagedResult.getLimit());
            list = userMapper.selectPage(map);
            if (list.size() > 0) {
                pagedResult.setDataList(list);
            }
        } catch (Exception e) {
            throw new RapidException(e);
        }

        return pagedResult;
    }
}
