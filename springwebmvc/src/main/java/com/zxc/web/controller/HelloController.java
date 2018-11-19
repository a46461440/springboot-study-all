package com.zxc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    /**
     * @Description 跳转主页
     * @Author Zhou RunMing
     * @Date 2018-11-19 11:47
     * @Param [name用户名]
     * @return {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/welcome")
    public ModelAndView index(String name, Model model) {
        model.addAttribute("message", "hello");
        return new ModelAndView("index");
    }

}
