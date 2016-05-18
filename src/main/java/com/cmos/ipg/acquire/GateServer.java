package com.cmos.ipg.acquire;

import com.cmos.ipg.mapper.ClientLogMapper;
import com.cmos.ipg.mapper.DataMapper;
import com.cmos.ipg.service.MQService;
import com.cmos.ipg.service.SocketService;
import com.cmos.ipg.utils.DataTool;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by jackl on 2016/4/27.
 */
@Component
public class GateServer {
    @Value("${com.cmos.acquire.port}")
    private int _acquirePort=8000;
    private Logger _logger;
    @Autowired
    DataMapper dataMapper;
    @Autowired
    ClientLogMapper clientLogMapper;
    @Autowired
    DataTool dataTool;
    @Autowired
    SocketService socketService;
    @Autowired
    MQService mqService;


    //生成数据
    ScheduledExecutorService nettyServerScheduledService = Executors.newScheduledThreadPool(10);

    public static ConcurrentHashMap<String,Channel> channels=new ConcurrentHashMap<String,io.netty.channel.Channel>();
    public   void start(){
        new NettySender(channels,socketService,dataTool).start();    //netty发数据线程
        new NettyServer(channels, _acquirePort, nettyServerScheduledService,dataMapper,clientLogMapper,socketService,dataTool).run();    //netty收数据程序，收到消息后可能导致阻塞的业务全部交由线程池处理
     }

}
