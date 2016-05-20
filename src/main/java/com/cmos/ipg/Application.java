package com.cmos.ipg;

import com.cmos.ipg.acquire.GateServer;
import com.cmos.ipg.dubbo.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


/**
 * Created by jackl on 2016/4/27.
 */

@SpringBootApplication
@ImportResource({ "classpath:dubbo-all.xml"})
public class Application implements CommandLineRunner {
    private Logger _logger;
    @Autowired
    private GateServer gateServer;
    @Value("${com.cmos.acquire.disabled}")
    private boolean _disabled;
    @Value("${com.cmos.acquire.port}")
    private int _port;

    @Autowired
    DemoService demoService;
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        this._logger = LoggerFactory.getLogger(Application.class);
        try {
            System.out.println(demoService.sayHello("ipg ipg"));
        }catch (Exception e){
            e.printStackTrace();
            this._logger.error("Dubbo Consumer filed:"+e.getMessage());
        }
        // 启动数据接受程序
        if(_disabled){
            return;
        }else{
            this._logger.info("GateWay Server is running,listening at port "+_port);
            gateServer.start();
        }

    }
}