package com.znn.provider;

import java.util.Date;

/**
 * Created by jackl on 2016/5/20.
 */
public class ReceiveAlarmServiceImpl implements ReceiveAlarmService {
    @Override
    public String send(String message) {
        System.out.println("receive:"+message);
        return "success"+new Date().getTime();
    }
}
