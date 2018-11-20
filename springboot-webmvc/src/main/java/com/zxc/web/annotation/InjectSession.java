package com.zxc.web.annotation;

import java.lang.annotation.*;

/**
 *
 * 需要注入JSession的类
 *
 * @NAME InjectSession
 * @DATE 2018/11/19 22:34
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectSession {
}
