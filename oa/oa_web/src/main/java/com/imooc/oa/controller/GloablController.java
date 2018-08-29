package com.imooc.oa.controller;

import com.imooc.oa.biz.GlobalBiz;
import com.imooc.oa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller("gloablController")
public class GloablController {

    @Autowired
    GlobalBiz globalBiz;

    /**
     * 请求登陆
     */
    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    /**
     * 登陆
     */
    @RequestMapping("/login")
    public String login(String sn, String password, HttpSession session) {
        Employee employee = globalBiz.login(sn,password);
        if (employee == null) {
            return "redirect:to_login";
        }
        session.setAttribute("employee",employee);
        return "redirect:self";
    }

    /**
     * 查看个人信息
     */
    @RequestMapping("/self")
    public String self() {
        return "self";
    }

    /**
     * 退出登录
     */
    @RequestMapping("/quit")
    public String quit(HttpSession session) {
        session.setAttribute("employee",null);
        return "redirect:to_login";
    }

    /**
     * 请求修改密码
     */
    @RequestMapping("/to_change_password")
    public String toChangePassword() {
        return "change_password";
    }

    /**
     * 修改密码
     */
    @RequestMapping("/change_password")
    public String changePassword(HttpSession session,String old,String new1,String new2) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee.getPassword().equals(old)) {
            // 旧密码正确
            if (new1 != null && new1.equals(new2)) {
                // 2次输入的新密码一致
                employee.setPassword(new1);
                // 修改密码
                globalBiz.changePassword(employee);
                // 重定向至个人信息界面
                return "redirect: self";
            }
        }
        // 重定向至修改密码界面
        return "redirect:to_change_password";

    }



}