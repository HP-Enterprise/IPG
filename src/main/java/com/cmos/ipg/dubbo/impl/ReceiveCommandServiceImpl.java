package com.cmos.ipg.dubbo.impl;

import com.cmos.ipg.dubbo.ReceiveCommandService;

import java.util.Date;

/**
 * Created by jackl on 2016/5/20.
 */

public class ReceiveCommandServiceImpl implements ReceiveCommandService {
    @Override
    public String sendCommand(String command) {
        System.out.println("receive command:"+command);
        return "success:"+new Date().getTime();
    }
}
