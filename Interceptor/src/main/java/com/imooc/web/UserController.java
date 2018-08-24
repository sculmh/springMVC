package com.imooc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/search")
    public String search(HttpSession session) {
        System.out.println("查询用户...");
        return "user/search";
    }

    @RequestMapping("/updatepwd")
    public String updatepwd(HttpSession session) {
        System.out.println("修改密码...");
        return "user/updatepwd";
    }

    @RequestMapping("/updateheaderPic")
    public String updateheaderPic(HttpSession session) {
        System.out.println("修改顶部图片...");
        return "user/updateheaderPic";
    }

    @RequestMapping("/updatebackground/{id}")
    public String updatebackground(HttpSession session) {
        System.out.println("修改背景图片...");
        return "user/test";
    }

}