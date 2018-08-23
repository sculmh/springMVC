package com.imooc.controller;

import com.imooc.dao.CourseDao;
import com.imooc.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DataBindController {

    @Autowired
    private CourseDao courseDao;

    @RequestMapping("/baseType")
    @ResponseBody
    public String baseType(@RequestParam("id") int id) {
        return "id=" + id;
    }

    @RequestMapping("/packageType")
    @ResponseBody
    public String packageType(Integer id) {
        return "id:" + id;
    }

  /*  @RequestMapping("/arrayType")
    @ResponseBody
    public String arrayType(String[] name) throws Exception{
        StringBuffer sb = new StringBuffer();
        for (String item: name) {
            byte[] bytes = item.getBytes("ISO-8859-1");
            String temp = new String(bytes,"UTF-8");
            sb.append(temp).append(" ");
        }
        return sb.toString();
    }
*/
    @RequestMapping("/pojoType")
    public ModelAndView pojoType(Course course) {
        System.out.println("访问");
        courseDao.add(course);
        System.out.println(course.toString());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("courses",courseDao.getAll());
        modelAndView.setViewName("index");
        return modelAndView;
    }


}
