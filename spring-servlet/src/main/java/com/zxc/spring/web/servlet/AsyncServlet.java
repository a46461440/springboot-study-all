package com.zxc.spring.web.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * 异步Servlet
 *
 * @author Zhou RunMing
 * @Date 2018-12-3
 */
@WebServlet(
        asyncSupported = true,//激活异步特性
        name = "asyncServlet",
        urlPatterns = "/async-servlet")
public class AsyncServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断是否支持异步
        if (req.isAsyncSupported()) {
            AsyncContext asyncContext = req.startAsync();
            asyncContext.setTimeout(50L);
            asyncContext.addListener(new AsyncListener() {
                @Override
                public void onComplete(AsyncEvent asyncEvent) throws IOException {
                    printlnT("执行完成");
                }

                @Override
                public void onTimeout(AsyncEvent asyncEvent) throws IOException {
                    HttpServletResponse httpServletResponse = (HttpServletResponse) asyncEvent.getSuppliedResponse();
                    httpServletResponse.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                    printlnT("执行超时");
                }

                @Override
                public void onError(AsyncEvent asyncEvent) throws IOException {
                    printlnT("执行错误");
                }

                @Override
                public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
                    printlnT("开始执行");
                }
            });
            ServletResponse response = asyncContext.getResponse();
            response.setContentType("text/plain;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("hello world");
            out.flush();
            out.close();
            asyncContext.complete();
        }
    }

    public static void printlnT(Object o) {
        String threadName = Thread.currentThread().getName();
        System.out.println("AsyncServlet[" + threadName + "]:" + o);
    }
}
