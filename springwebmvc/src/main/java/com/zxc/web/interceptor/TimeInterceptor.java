package com.zxc.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 每次请求处理时间
 *
 * @Author Zhou RunMing
 * @Description
 * @Date 2018-11-19 17:52
 **/
public class TimeInterceptor implements HandlerInterceptor {
    private static ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        startTime.set(System.currentTimeMillis());
        return true;
    }

    /**
     * @Description handlerChain执行时间
     * @Author Zhou RunMing
     * @Date 2018-11-19 17:59
     * @Param [request, response, handler, modelAndView]
     * @return void
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String methodName = handlerMethod.getMethod().getName();
        String beanName = handlerMethod.getBean().getClass().getName();
        System.out.print(beanName+"."+methodName +
                "方法执行耗时" + String.valueOf(System.currentTimeMillis() - startTime.get()));
    }
}
