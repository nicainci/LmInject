package com.lm.annotation.provide;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author WWC
 * @Create 2018/8/30
 * @Description 提供给其他地方使用这个字段的注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface Provide {
    String provideName();
}
