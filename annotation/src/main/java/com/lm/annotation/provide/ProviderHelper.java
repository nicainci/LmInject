package com.lm.annotation.provide;

/**
 * @Author WWC
 * @Create 2018/8/31
 * @Description 供应者对象帮助类
 */
public class ProviderHelper {

    /**
     * 获取供应者对象中的供应名提供的值
     *
     * @param providerObject 供应者对象
     * @param provideName    供应名
     */
    public static <T, R> R get(T providerObject, String provideName) {
        return (R) Providers.get(providerObject.getClass()).get(providerObject, provideName);
    }

}
