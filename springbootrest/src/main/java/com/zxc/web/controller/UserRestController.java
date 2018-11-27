package com.zxc.web.controller;

import com.zxc.web.domain.po.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * User {@link RestController}
 *
 * @author Zhou RunMing
 * @Date 2018-11-27
 */
@RestController
public class UserRestController {

    @PostMapping("/echo/user")
    public User getUser(@RequestBody User user) {
        return user;
    }

}
