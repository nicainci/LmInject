package com.lm.lminject;

import com.lm.annotation.provide.Provide;

/**
 * @Author WWC
 * @Create 2018/10/18
 * @Description A供应器
 * Copyright(c) 2017, Zhejiang Yunbo Technology Co.,Ltd. All rights reserved
 */
public class AProvide {

    @Provide(provideName = "integer")
    int integer = 1024;

    @Provide(provideName = "integerArray")
    int[] integerArray = new int[]{1024, 1025};

    @Provide(provideName = "long")
    long aLong = 2048;

    @Provide(provideName = "longArray")
    long[] longArray = new long[]{2048, 2049};

    @Provide(provideName = "char")
    char aChar = 'A';

    @Provide(provideName = "charArray")
    char[] charArray = new char[]{'A', 'B', 'C'};

    @Provide(provideName = "boolean")
    boolean aBoolean = true;

    @Provide(provideName = "booleanArray")
    boolean[] booleanArray = new boolean[]{true, false, true, false};

    @Provide(provideName = "byte")
    byte aByte = 2;

    @Provide(provideName = "byteArray")
    byte[] byteArray = new byte[]{2, 3, 4};

}
