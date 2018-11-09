package com.lm.lminject;

import com.lm.annotation.provide.Provide;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author WWC
 * @Create 2018/10/18
 * @Description B供应器
 * Copyright(c) 2017, Zhejiang Yunbo Technology Co.,Ltd. All rights reserved
 */
public class BProvide {
    @Provide(provideName = "string")
    String string = "str";
    @Provide(provideName = "stringArray")
    String[] stringArray = new String[]{"str1", "str2"};
    @Provide(provideName = "stringList")
    List<String> stringList = new ArrayList<>();

    {
        stringList.add("str3");
        stringList.add("str4");
        stringList.add("str5");
    }

    @Provide(provideName = "float")
    float aFloat = 123.123f;
    @Provide(provideName = "floatArray")
    float[] floatArray = new float[]{1, 2, 3};
    @Provide(provideName = "double")
    double aDouble = 1234.1234d;
    @Provide(provideName = "doubleArray")
    double[] doubleArray = new double[]{1, 2, 3, 4};
}
