package com.mmal.concurrency.annoatons;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 线程安全注解 只用于标记
 */
@Target(ElementType.TYPE)//作用于类上
@Retention(RetentionPolicy.SOURCE)//指定了存在 只作为标识 不适用在编程中
public @interface ThreadSafe {

    /**
     * 需要增加默认值
     * @return str
     */
    String value() default "";

}
