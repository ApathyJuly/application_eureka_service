package com.example.service_hi.user.controller;

import com.example.service_hi.user.entity.User;
import com.example.service_hi.user.entity.dto.Result;
import com.example.service_hi.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-11-30 11:29:20
 */

@Controller
@RequestMapping(value = "/userController")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    @ResponseBody
    public User selectOne(String id, HttpServletRequest request) {
        //获取当前用户id
        String currentUserId = (String) request.getSession().getAttribute("currentUserId");
        return userService.queryById(id);
    }

    /*登录*/
    @RequestMapping("toLogin")
    public String toLogin() {
        return "index";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "redirect:/user/tologin";
    }
}