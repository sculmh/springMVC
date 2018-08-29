package com.imooc.oa.controller;

import com.imooc.oa.biz.DepartmentBiz;
import com.imooc.oa.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("departmentController")
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentBiz departmentBiz;

    /**
     * 查看所有部门信息
     */
    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("list",departmentBiz.getAll());
        return "department_list";
    }

    /**
     * 请求增加部门
     */
    @RequestMapping("/to_add")
    public String toAdd(Model model) {
        model.addAttribute("department",new Department());
        return "department_add";
    }

    /**
     * 增加部门
     */
    @RequestMapping("/add")
    public String add(Department department) {
        departmentBiz.add(department);
        return "redirect:list";
    }

    /**
     * 请求修改部门信息
     */
    @RequestMapping("/to_update")
    public String toUpdate(String sn,Model model) {
        model.addAttribute("department",departmentBiz.get(sn));
        return "department_update";
    }

    /**
     * 修改部门信息
     */
    @RequestMapping("/update")
    public String update(Department department) {
        departmentBiz.edit(department);
        return "redirect:list";
    }

    /**
     * 删除部门
     */
    @RequestMapping("/remove")
    public String remove(String sn) {
        departmentBiz.remove(sn);
        return "redirect:list";
    }


}