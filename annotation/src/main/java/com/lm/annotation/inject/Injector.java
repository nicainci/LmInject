package com.lm.annotation.inject;

/**
 * @Author WWC
 * @Create 2018/8/31
 * @Description 注入器接口
 */
public interface Injector<T> {
    /**
     * 注入
     *
     * @param injectTarget   要注入的目标
     * @param providerObject 供应者对象
     */
    void inject(T injectTarget, Object providerObject);
}
