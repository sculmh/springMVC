package com.imooc.oa.controller;

import com.imooc.oa.biz.DepartmentBiz;
import com.imooc.oa.biz.EmployeeBiz;
import com.imooc.oa.entity.Employee;
import com.imooc.oa.global.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("employeeController")
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeBiz employeeBiz;

    @Autowired
    DepartmentBiz departmentBiz;

    /**
     * 显示所有员工信息
     */
    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("list",employeeBiz.getAll());
        return "employee_list";
    }

    /**
     * 请求增加员工
     */
    @RequestMapping("/to_add")
    public String toAdd(Model model) {
        model.addAttribute("employee",new Employee());
        model.addAttribute("dlist",departmentBiz.getAll());
        model.addAttribute("plist", Constant.getPosts());
        return "employee_add";
    }

    /**
     * 增加员工
     */
    @RequestMapping("/add")
    public String add(Employee employee) {
        employeeBiz.add(employee);
        return "redirect:list";
    }

    /**
     * 请求修改员工信息
     */
    @RequestMapping("/to_update")
    public String toUpdate(String sn, Model model) {
        model.addAttribute("employee",employeeBiz.get(sn));
        model.addAttribute("dlist",departmentBiz.getAll());
        model.addAttribute("plist", Constant.getPosts());
        return "employee_update";
    }

    /**
     * 修改员工信息
     */
    @RequestMapping("/update")
    public String update(Employee employee) {
        employeeBiz.edit(employee);
        return "redirect:list";
    }

    /**
     * 删除员工信息
     */
    @RequestMapping("/remove")
    public String remove(String sn) {
        employeeBiz.remove(sn);
        return "redirect:list";
    }


}