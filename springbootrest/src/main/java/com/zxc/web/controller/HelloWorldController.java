package com.zxc.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * {@link RestController}
 *
 * @author Zhou RunMing
 * @date 2018/11/26
 */
@RestController
public class HelloWorldController {

    @GetMapping("/hello-world")
    public String helloWorld(@RequestParam(required = false) String message, String name) {
        return "hello world:" + name + ":" + message;
    }

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

//    @CrossOrigin("*")
    @GetMapping("/hello")
    public String returnSomething() {
        return "hello";
    }

}
