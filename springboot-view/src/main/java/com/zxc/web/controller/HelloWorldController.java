package com.zxc.web.controller;

import com.zxc.web.annotation.InjectSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * {@link Controller}
 *
 * @author Zhou RunMing
 * @date 2018/11/21
 */
@Controller
@InjectSession
public class HelloWorldController {

    private int i = 0;

    {
        new Thread(()->{
            i++;
        }).start();
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "hello-world";
    }

    @ModelAttribute("message")
    public String message() {
        return "hello world" + i;
    }

}
