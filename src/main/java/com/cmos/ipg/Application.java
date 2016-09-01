package com.cmos.ipg;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.cmos.ipg.acquire.GateServer;
import com.cmos.ipg.dubbo.ReceiveAlarmService;
import com.cmos.ipg.service.MqConsumerService;
import com.cmos.ipg.utils.Base64;

import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.yaml.snakeyaml.Yaml;


/**
 * Created by jackl on 2016/4/27.
 */

@SpringBootApplication
@ImportResource({ "classpath:dubbo-all.xml"})
public class Application  implements CommandLineRunner {
    private Logger _logger;
    @Autowired
    private GateServer gateServer;
    @Autowired
    private MqConsumerService consumerService;
    @Autowired
    DataSource dataSource;
    @Value("${com.cmos.acquire.disabled}")
    private boolean _disabled;
    @Value("${com.cmos.acquire.port}")
    private int _port;

    public static void main(String[] args) {
    	ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    }
   
    @Override
    public void run(String... args) throws Exception {
        this._logger = LoggerFactory.getLogger(Application.class);
        System.out.println(_port);
        // 启动数据接受程序
        if(_disabled){
            return;
        }else{
            this._logger.info("GateWay Server is running,listening at port "+_port);
            consumerService.start();
            gateServer.start();
        }

    }
      
}