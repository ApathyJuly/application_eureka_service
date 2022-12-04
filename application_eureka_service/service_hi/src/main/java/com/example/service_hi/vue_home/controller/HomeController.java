package com.example.service_hi.vue_home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/vue/HomeController")
public class HomeController {
    /**
     * 跳转到vue开始界面
     *
     * @return
     */
    @RequestMapping("toHome")
    public String toLogin() {
        return "vue/home";
    }
}
