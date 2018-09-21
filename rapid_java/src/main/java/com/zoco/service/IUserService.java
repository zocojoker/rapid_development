package com.zoco.service;

import com.zoco.vo.PagedResultEntity;
import com.zoco.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户类处理业务
 *
 * @author zoco
 * @creat 2018-09-19-11:43
 */
public interface IUserService {
    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    User Login(String username, String password);

    /**
     * 用户注销(返回-1失败)
     *
     * @return
     */
    int LoginOut(HttpServletRequest request, HttpServletResponse response);

    /**
     * 删除用户大于0成功
     *
     * @param user_id
     * @return
     */
    int delUser(String user_id);

    /**
     * 大于零成功
     *
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 修改基本信息
     *
     * @param user
     * @return
     */
    int updateUserMessage(User user);


    /**
     * 获取用户列表
     *
     * @param map
     * @return
     */
    PagedResultEntity<User> selectPage(PagedResultEntity<User> pagedResult);

}
