package com.cmos.ipg.dubbo;


import com.alibaba.dubbo.config.annotation.Service;

/**
 * Created by jackl on 2016/5/19.
 */

public class TestRegistryServiceImpl  {
    public String hello(String name) {
        return "hello"+name;
    }
}
