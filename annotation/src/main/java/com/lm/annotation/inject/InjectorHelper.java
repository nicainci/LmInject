package com.lm.annotation.inject;

import com.lm.annotation.provide.Provider;
import com.lm.annotation.provide.Providers;

/**
 * @Author WWC
 * @Create 2018/11/9
 * @Description 注入器帮助类
 * Copyright(c) 2017, Zhejiang Yunbo Technology Co.,Ltd. All rights reserved
 */
public class InjectorHelper {
    /**
     * 注入
     *
     * @param injectTarget   要注入的目标
     * @param providerObject 供应者对象
     */
    public static void inject(Object injectTarget, Object providerObject) {

        if (Injectors.DEFAULT == Injectors.get(injectTarget.getClass())) {
            String className = injectTarget.getClass().getName() + "Injector";
            try {
                Class clazz = Class.forName(className);
                Injectors.put(injectTarget.getClass(), (Injector) clazz.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (Providers.DEFAULT == Providers.get(providerObject.getClass())) {
            String className = providerObject.getClass().getName() + "Provider";
            try {
                Class clazz = Class.forName(className);
                Providers.put(providerObject.getClass(), (Provider) clazz.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Injectors.get(injectTarget.getClass()).inject(injectTarget, providerObject);
    }
}
