package com.imooc.controller;

import com.imooc.dao.CourseDao;
import com.imooc.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;

@Controller
public class CourseController {

    @Autowired
    private CourseDao courseDao;

    @PostMapping("/add")
    public String add(Course course) {
        courseDao.add(course);
        return "redirect:/getAll";
    }

    @GetMapping("/getAll")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("courses",courseDao.getAll());
        return modelAndView;
    }

    /**
     * 通过id查询课程
     */
    @GetMapping("/getById/{id}")
    public ModelAndView getById(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("course",courseDao.getById(id));
        return modelAndView;
    }

    /**
     * 删除课程
     */
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        courseDao.deleteById(id);
        return "redirect:/getAll";
    }

    /**
     * 修改课程
     */
    @PutMapping("/update")
    public String update(Course course) {
        courseDao.update(course);
        LinkedList<Integer> list = new LinkedList<>();
        return "redirect:/getAll";
    }
}
