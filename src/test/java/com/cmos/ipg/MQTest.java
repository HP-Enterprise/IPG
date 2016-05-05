package com.cmos.ipg;

import com.cmos.ipg.mq.Consumer;
import com.cmos.ipg.mq.Producer;
import com.cmos.ipg.service.MQService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackl on 2016/4/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class MQTest {
    @Autowired
    Consumer consumer;
    @Autowired
    Producer producer;

    @Autowired
    MQService mqService;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test_sendMsg(){
        mqService.pushToUser(1,"WARNING>>>>");

    }



}
