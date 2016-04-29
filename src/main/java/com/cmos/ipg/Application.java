package com.cmos.ipg;

import com.cmos.ipg.acquire.GateServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Created by jackl on 2016/4/27.
 */

@SpringBootApplication
public class Application implements CommandLineRunner {
    private Logger _logger;
    @Autowired
    private GateServer gateServer;
    @Value("${com.comos.acquire.disabled}")
    private boolean _disabled;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        this._logger = LoggerFactory.getLogger(Application.class);
        this._logger.info("Application is running...");
        // 启动数据接受程序
        if(_disabled){
            return;
        }else{
            gateServer.start();
        }

    }
}