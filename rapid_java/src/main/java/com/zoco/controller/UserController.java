package com.zoco.controller;

import com.zoco.vo.ResponseEntity;
import com.zoco.common.Code;
import com.zoco.entity.User;
import com.zoco.service.IUserService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 用户处理控制
 *
 * @author zoco
 * @creat 2018-09-21-11:32
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/login.do")
    @ResponseBody
    public ResponseEntity userLogin(HttpServletRequest request, HttpServletResponse response, User user) {
        response.setCharacterEncoding("utf-8");
        ResponseEntity resEntity = new ResponseEntity();
        HttpSession session = request.getSession();
        Map map = new HashedMap();
        try {
            user = userService.Login(user.getUsername(), user.getPassword());
            if (user == null) {
                resEntity.setCode(Code.CODE_PWD_NOT_CORRECT);
                resEntity.setMessage("密码或账号错误");
                return resEntity;
            } else {
                //置空密码。敏感信息
                user.setPassword("");
                session.setAttribute("userinfo", user);
                map.put("userinfo", user);
                resEntity.setReceipt(map);
                resEntity.setCode(Code.CODE_OK);
            }
        } catch (Exception e) {
            resEntity.setCode(Code.CODE_SERVER_ERROR);
            resEntity.setMessage(e.getMessage());
            log.error("登录异常：", e.getMessage(), e);
        }
    }
}
