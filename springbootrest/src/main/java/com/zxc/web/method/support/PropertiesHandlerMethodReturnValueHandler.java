package com.zxc.web.method.support;

import com.zxc.web.http.converter.properties.PropertiesHttpMessageConverter;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * {@link Properties} 的{@link HandlerMethodReturnValueHandler}的实现
 *
 * @author Zhou RunMing
 * @date 2018/11/29
 */
public class PropertiesHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        //判断是否匹配
        return methodParameter.getMethod().getReturnType() == Properties.class;
    }

    @Override
    public void handleReturnValue(@Nullable Object o, MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest) throws Exception {
        ServletWebRequest servletWebRequest = (ServletWebRequest) nativeWebRequest;
        HttpServletRequest request = servletWebRequest.getRequest();
        HttpServletResponse response = servletWebRequest.getResponse();
        String contentType = request.getHeader("Content-Type");
        MediaType mediaType = MediaType.parseMediaType(contentType);
        Charset charset = mediaType.getCharset();
        charset = charset == null ? Charset.forName("UTF-8") : charset;
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputMessage.getBody(), charset);
        Properties properties = (Properties) o;
        properties.store(outputStreamWriter, "From PropertiesHandlerMethodReturnValueHandler");
        //告知SpringMvc当前请求已经完毕
        modelAndViewContainer.setRequestHandled(true);
    }
}
