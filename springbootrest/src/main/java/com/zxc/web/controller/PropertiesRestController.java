package com.zxc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * Properties {@link RestController}
 *
 * @author Zhou RunMing
 * @Date 2018-11-28
 */
//@RestController
@Controller
public class PropertiesRestController {

    @PostMapping(value = "/add/properties"
            ,consumes = "text/properties;charset=UTF-8")
    public Properties getUser(
//            @RequestBody
                                          Properties properties) {
        final StringBuilder result = new StringBuilder(100);
        properties.entrySet().stream().forEach(entry ->
                result.append(entry.getKey()).append(":").append(entry.getValue()).append("\r\n"));
        return properties;
    }

}
