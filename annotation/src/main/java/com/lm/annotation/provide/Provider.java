package com.lm.annotation.provide;

/**
 * @Author WWC
 * @Create 2018/8/30
 * @Description 供应者接口
 */
public interface Provider<T> {
    /**
     * 从供应者对象中获取object类型供应值
     *
     * @param providerObject 供应者对象
     * @param provideName    供应名
     */
    <R> R get(T providerObject, String provideName);

    /**
     * 从供应者对象中获取int类型供应值
     *
     * @param providerObject 供应者对象
     * @param provideName    供应名
     */
    int getInt(T providerObject, String provideName);

    /**
     * 从供应者对象中获取long类型供应值
     *
     * @param providerObject 供应者对象
     * @param provideName    供应名
     */
    long getLong(T providerObject, String provideName);

    /**
     * 从供应者对象中获取char类型供应值
     *
     * @param providerObject 供应者对象
     * @param provideName    供应名
     */
    char getChar(T providerObject, String provideName);

    /**
     * 从供应者对象中获取byte类型供应值
     *
     * @param providerObject 供应者对象
     * @param provideName    供应名
     */
    byte getByte(T providerObject, String provideName);

    /**
     * 从供应者对象中获取boolean类型供应值
     *
     * @param providerObject 供应者对象
     * @param provideName    供应名
     */
    boolean getBoolean(T providerObject, String provideName);

    /**
     * 从供应者对象中获取float类型供应值
     *
     * @param providerObject 供应者对象
     * @param provideName    供应名
     */
    float getFloat(T providerObject, String provideName);

    /**
     * 从供应者对象中获取double类型供应值
     *
     * @param providerObject 供应者对象
     * @param provideName    供应名
     */
    double getDouble(T providerObject, String provideName);
}
