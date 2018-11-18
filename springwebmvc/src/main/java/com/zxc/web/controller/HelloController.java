package com.zxc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @HelloWorld {@link Controller}
 */
@Controller
public class HelloController {

    @RequestMapping("/welcome")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

}
