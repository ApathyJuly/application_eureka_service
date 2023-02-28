package com.example.service_hi.fileHome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/fileHome")
public class FileHome {
    @RequestMapping("toFileHome")
    public String toLogin() {
        return "webFilePage/file/fileListPage";
    }
}
