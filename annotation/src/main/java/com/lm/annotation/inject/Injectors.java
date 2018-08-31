package com.lm.annotation.inject;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author WWC
 * @Create 2018/8/31
 * @Description 注入器集合
 */
public class Injectors {

    /**
     * 默认的注入器，不提供任何操作
     * 为了防止{@link Injectors#get(Class)}产生空值
     */
    public static final Injector DEFAULT = new Injector() {
        @Override
        public void inject(Object injectTarget, Object providerObject) {

        }
    };

    /**
     * 根据要注入的类来保存对于的注入器对象
     */
    private static Map<Class, Injector> injectorMap = new HashMap<>();

    /**
     * 根据要注入的类保存Provider对象
     *
     * @param injectClass 要注入的类
     * @param injector    注入器对象
     */
    public static void put(Class injectClass, Injector injector) {
        injectorMap.put(injectClass, injector);
    }

    /**
     * 获取注入器对象
     *
     * @param injectClass 要注入的类
     */
    public static Injector get(Class injectClass) {
        Injector injector = injectorMap.get(injectClass);
        if (injector == null) {
            injector = DEFAULT;
        }
        return injector;
    }
}
