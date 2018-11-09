package com.lm.annotation.provide;

/**
 * @Author WWC
 * @Create 2018/8/31
 * @Description 供应者对象帮助类
 */
public class ProviderHelper {

    /**
     * 获取供应者对象中的供应名提供的object值
     *
     * @param providerObject 供应者对象
     * @param provideName    供应名
     */
    public static <T, R> R get(T providerObject, String provideName) {
        return (R) Providers.get(providerObject.getClass()).get(providerObject, provideName);
    }

    /**
     * 获取供应者对象中的供应名提供的int值
     *
     * @param providerObject 供应者对象
     * @param provideName    供应名
     */
    public static <T> int getInt(T providerObject, String provideName) {
        return Providers.get(providerObject.getClass()).getInt(providerObject, provideName);
    }

    /**
     * 获取供应者对象中的供应名提供的long值
     *
     * @param providerObject 供应者对象
     * @param provideName    供应名
     */
    public static <T> long getLong(T providerObject, String provideName) {
        return Providers.get(providerObject.getClass()).getLong(providerObject, provideName);
    }

    /**
     * 获取供应者对象中的供应名提供的char值
     *
     * @param providerObject 供应者对象
     * @param provideName    供应名
     */
    public static <T> char getChar(T providerObject, String provideName) {
        return Providers.get(providerObject.getClass()).getChar(providerObject, provideName);
    }

    /**
     * 获取供应者对象中的供应名提供的byte值
     *
     * @param providerObject 供应者对象
     * @param provideName    供应名
     */
    public static <T> byte getByte(T providerObject, String provideName) {
        return Providers.get(providerObject.getClass()).getByte(providerObject, provideName);
    }

    /**
     * 获取供应者对象中的供应名提供的boolean值
     *
     * @param providerObject 供应者对象
     * @param provideName    供应名
     */
    public static <T> boolean getBoolean(T providerObject, String provideName) {
        return Providers.get(providerObject.getClass()).getBoolean(providerObject, provideName);
    }

    /**
     * 获取供应者对象中的供应名提供的float值
     *
     * @param providerObject 供应者对象
     * @param provideName    供应名
     */
    public static <T> float getFloat(T providerObject, String provideName) {
        return Providers.get(providerObject.getClass()).getFloat(providerObject, provideName);
    }

    /**
     * 获取供应者对象中的供应名提供的double值
     *
     * @param providerObject 供应者对象
     * @param provideName    供应名
     */
    public static <T> double getDouble(T providerObject, String provideName) {
        return Providers.get(providerObject.getClass()).getDouble(providerObject, provideName);
    }
}
