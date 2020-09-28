package com.ly.controller;

import com.ly.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("student")
public class StudentController {
    @Resource(name = "studentService")
    private StudentService studentService;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getAllStudent() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("students");
        mv.addObject("students", studentService.getAllStudent());
        return mv;


    }

}
