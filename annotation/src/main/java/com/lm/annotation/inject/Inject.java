package com.lm.annotation.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author WWC
 * @Create 2018/8/30
 * @Description 注入注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface Inject {
    /**
     * 供应注解提供的供应名
     */
    String provideName();
}
