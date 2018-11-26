package com.zxc.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * {@link RestController}
 *
 * @author Zhou RunMing
 * @date 2018/11/26
 */
@RestController
public class HelloWorldController {

    @GetMapping("/hello-world")
    public String helloWorld(@RequestParam(required = false) String message) {
        return "hello world:" + message;
    }

}
