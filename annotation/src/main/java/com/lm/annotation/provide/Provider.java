package com.lm.annotation.provide;

/**
 * @Author WWC
 * @Create 2018/8/30
 * @Description 供应者接口
 */
public interface Provider<T> {
    /**
     * 从供应者对象中获取供应值
     *
     * @param providerObject 供应者对象
     * @param provideName    供应名
     */
    <R> R get(T providerObject, String provideName);
}
