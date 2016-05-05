package com.cmos.ipg.service;

import com.cmos.ipg.service.MQTTHome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jackl on 2016/5/5.
 */

/**
 * MQTT业务层接口
 */
@Service
public class MQService{
    @Autowired
    MQTTHome mqttHome;


    public void pushToUser(int userId, String content) {
        System.out.println(content);
        try{
            mqttHome.sendMessage(content);
        }catch (Exception e){e.printStackTrace();}
    }


}
