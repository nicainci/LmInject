package com.lm.lminject;

import com.lm.annotation.provide.Provide;

/**
 * @Author WWC
 * @Create 2018/8/30
 * @Description 描述
 */
public class MainProvide {
    @Provide(provideName = "userName")
    String userName = "root";

    @Provide(provideName = "pwd")
    String pwd ="asd123456";
}
