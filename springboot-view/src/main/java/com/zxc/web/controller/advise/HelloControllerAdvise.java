package com.zxc.web.controller.advise;

import com.zxc.web.annotation.InjectSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * 注解{@link InjectSession}的专属通知
 *
 * @NAME HelloControllerAdvise
 * @DATE 2018/11/19 22:31
 */
@ControllerAdvice(annotations = {InjectSession.class})
public class HelloControllerAdvise {

    @ModelAttribute("acceptLanguage")
    public String setAcceptLanguage(@RequestHeader("Accept-Language") String acceptLanguage) {
        return acceptLanguage;
    }

    @ModelAttribute("jSessionId")
    public String setCookie(@CookieValue(value = "JSESSIONID", required = false) String jSessionId) {
        return jSessionId;
    }

    @ModelAttribute("message")
    public String setMessage() {
        return "Hello,world";
    }

    /**
     * @Description 自定义全局异常
     * @Author Zhou RunMing
     * @Date 2018/11/19 23:09
     * @Param [throwable 抛出的异常]
     * @Return org.springframework.http.ResponseEntity<java.lang.String>
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> onException(Throwable throwable) {
        return ResponseEntity.ok(throwable.getMessage());
    }

}
