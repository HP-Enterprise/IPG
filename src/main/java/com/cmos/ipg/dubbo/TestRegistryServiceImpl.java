package com.cmos.ipg.dubbo;

import com.alibaba.dubbo.config.annotation.Service;

import java.util.Date;


/**
 * Created by jackl on 2016/5/19.
 */
@Service(interfaceName="testRegistryService")
public class TestRegistryServiceImpl  {
    public String hello(String name) {
        return "hello"+new Date().toLocaleString();
    }
}
