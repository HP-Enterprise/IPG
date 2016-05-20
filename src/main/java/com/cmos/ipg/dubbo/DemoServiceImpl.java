package com.cmos.ipg.dubbo;

import com.alibaba.dubbo.config.annotation.Service;

import java.util.Date;

/**
 * Created by jackl on 2016/5/19.
 */
@Service()
public class DemoServiceImpl implements DemoService{
    @Override
    public String sayHello(String name) {
        String hello="Hello Dubbo,Hello "+name+",now "+new Date().toLocaleString();
        return hello;
    }
}
