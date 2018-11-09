package com.lm.annotation.provide;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author WWC
 * @Create 2018/8/30
 * @Description 供应者对象的集合
 */
public class Providers {

    /**
     * 默认的供应者，不提供任何操作
     * 为了防止{@link Providers#get(Class)}产生空值
     */
    public static final Provider DEFAULT = new Provider() {
        @Override
        public int getInt(Object providerObject, String provideName) {
            return 0;
        }

        @Override
        public long getLong(Object providerObject, String provideName) {
            return 0;
        }

        @Override
        public char getChar(Object providerObject, String provideName) {
            return 0;
        }

        @Override
        public byte getByte(Object providerObject, String provideName) {
            return 0;
        }

        @Override
        public boolean getBoolean(Object providerObject, String provideName) {
            return false;
        }

        @Override
        public float getFloat(Object providerObject, String provideName) {
            return 0;
        }

        @Override
        public double getDouble(Object providerObject, String provideName) {
            return 0;
        }

        @Override
        public Object get(Object providerClass, String provideName) {
            return null;
        }
    };

    /**
     * 根据要供应内容的类来保存对应的供应者对象
     */
    private static Map<Class, Provider> providerMap = new HashMap<>();

    /**
     * 根据供应者的类保存供应者对象
     *
     * @param provideClass 要供应内容的类
     * @param provider     供应者对象
     */
    public static void put(Class provideClass, Provider provider) {
        providerMap.put(provideClass, provider);
    }

    /**
     * 获取供应者对象
     *
     * @param provideClass 要供应内容的类
     */
    public static Provider get(Class provideClass) {
        Provider provider = providerMap.get(provideClass);
        if (provider == null) {
            provider = DEFAULT;
        }
        return provider;
    }

}
