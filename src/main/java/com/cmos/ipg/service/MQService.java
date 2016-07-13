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

    /**
     * push Mq
     * @param userId
     * @param content 消息内容
     * @param topic 主题
     * @param tag 标签
     * @param key 消息关键词(可为空)
     */
    public void pushToUser(int userId, String content,String topic,String tag,String key) {
        System.out.println(content);
        try{
            mqttHome.sendMessage(content,topic,tag,key);
        }catch (Exception e){e.printStackTrace();}
    }


}
