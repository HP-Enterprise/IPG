package com.cmos.ipg;

import com.cmos.ipg.bean.HeartBeatReq;
import com.cmos.ipg.utils.DataTool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackl on 2016/4/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class BeanTest {
    @Autowired
    DataTool dataTool;
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test_getBytes(){
        HeartBeatReq heartBeatReq=new HeartBeatReq();
        heartBeatReq.setLength((short)10);
        heartBeatReq.setSendingTime(1443151834);
        heartBeatReq.setMessageType((byte) 3);
        heartBeatReq.setAgentNum((byte) 3);
        heartBeatReq.setHeartBeat((byte) 3);
        heartBeatReq.setCheckSum((byte) 3);
        System.out.println(dataTool.bytes2hex(heartBeatReq.encoded()));
    }
}
