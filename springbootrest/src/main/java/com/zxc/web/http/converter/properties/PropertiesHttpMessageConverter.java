package com.zxc.web.http.converter.properties;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * {@link Properties}的{@link HttpMessageConverter}的实现
 *
 * @author Zhou RunMing
 * @date 2018/11/28
 */
public class PropertiesHttpMessageConverter extends AbstractGenericHttpMessageConverter<Properties> {

    public PropertiesHttpMessageConverter() {
        //设置可以处理的类型
        super(new MediaType("text", "properties"));
    }

    @Override
    protected void writeInternal(Properties properties, @Nullable Type type, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        HttpHeaders headers = httpOutputMessage.getHeaders();
        MediaType mediaType = headers.getContentType();
        Charset charset = mediaType.getCharset();
        charset = charset == null ? Charset.forName("UTF-8") : charset;
        Writer outputStreamWriter = new OutputStreamWriter(httpOutputMessage.getBody(),charset);
        properties.store(outputStreamWriter, "From PropertiesHttpMessageConverter");
    }

    @Override
    protected Properties readInternal(Class<? extends Properties> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {

        InputStream body = httpInputMessage.getBody();
        HttpHeaders headers = httpInputMessage.getHeaders();
        MediaType mediaType = headers.getContentType();
        Charset charset = mediaType.getCharset();
        charset = charset == null ? Charset.forName("UTF-8") : charset;
        InputStreamReader inputStreamReader = new InputStreamReader(body, charset);
        Properties properties = new Properties();
        properties.load(inputStreamReader);
        return properties;
    }

    @Override
    public Properties read(Type type, @Nullable Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return this.readInternal(null, httpInputMessage);
    }
}
