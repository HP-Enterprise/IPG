package com.cmos.ipg.service;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * Created by jackl on 2016/5/5.
 */
@Service
public class MQTTHome {
//    private @Value("${com.hp.mqtt.producerGroupName}") String producerGroupName;
//    private @Value("${com.hp.mqtt.nameServerAddress}") String nameServerAddress;
//    private @Value("${com.hp.mqtt.instanceName}") String instanceName;

    private  String producerGroupName="ProducerGroupName";
    private  String nameServerAddress="127.0.0.1:9876";
    private  String instanceName="Producer";

    DefaultMQProducer producer=null;

    private void init()throws MQClientException,
            InterruptedException {
        producer = new DefaultMQProducer(producerGroupName);
        producer.setNamesrvAddr(nameServerAddress);
        producer.setInstanceName(instanceName);
        /**
         * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         * 注意：切记不可以在每次发送消息时，都调用start方法
         */
        producer.start();
    }
    public void sendMessage(String message) throws MQClientException,
    InterruptedException ,MQBrokerException,RemotingException {
        if (producer==null){
            init();
        }
        Message msg = new Message("alarm",// topic
                "TagA",// tag
                "OrderID001",// key
                message.getBytes());// body
        SendResult sendResult = producer.send(msg);
        System.out.println(sendResult);
    }
}
