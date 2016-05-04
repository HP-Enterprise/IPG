package com.cmos.ipg;

import com.cmos.ipg.bean.*;
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
    public void test_HeartBeatReq(){
        HeartBeatReq heartBeatReq=new HeartBeatReq();
        heartBeatReq.setSendingTime(1443151834);
        heartBeatReq.setEventId(1443151834);
        heartBeatReq.setAgentNum((byte) 3);
        heartBeatReq.setHeartBeat((byte) 3);
        byte[] bytes=heartBeatReq.encoded();
        System.out.println(dataTool.bytes2hex(bytes));
        HeartBeatReq h=new HeartBeatReq();
        h.decoded(bytes);
        System.out.println(h.toString());
        assert (h.getAgentNum().equals((byte)3));
    }

    @Test
    public void test_HeartBeatResp(){
        HeartBeatResp heartBeatResp=new HeartBeatResp();
        heartBeatResp.setSendingTime(1443151834);
        heartBeatResp.setEventId(1443151834);
        heartBeatResp.setAgentNum((byte) 3);
        heartBeatResp.setStatus((byte) 1);
        byte[] bytes=heartBeatResp.encoded();
        System.out.println(dataTool.bytes2hex(bytes));
        HeartBeatResp h=new HeartBeatResp();
        h.decoded(bytes);
        System.out.println(h.toString());
        assert (h.getAgentNum().equals((byte)3));
    }

    @Test
    public void test_ParamDownloadReq(){
        ParamDownloadReq paramDownloadReq=new ParamDownloadReq();
        paramDownloadReq.setSendingTime(1443151834);
        paramDownloadReq.setEventId(1443151834);
        paramDownloadReq.setAgentNum((byte) 3);
        paramDownloadReq.setParamDownload((byte) 4);
        byte[] bytes=paramDownloadReq.encoded();
        System.out.println(dataTool.bytes2hex(bytes));
        ParamDownloadReq h=new ParamDownloadReq();
        h.decoded(bytes);
        System.out.println(h.toString());
        assert (h.getAgentNum().equals((byte)3));
    }

    @Test
    public void test_ParamDownloadResp(){
        ParamDownloadResp paramDownloadResp=new ParamDownloadResp();
        paramDownloadResp.setSendingTime(1443151834);
        paramDownloadResp.setEventId(1443151834);
        paramDownloadResp.setAgentNum((byte) 3);
        paramDownloadResp.setStatus((byte) 1);
        paramDownloadResp.setCollectContab(10);
        paramDownloadResp.setCollectProtocol((byte) 1);
        byte[] bytes=paramDownloadResp.encoded();
        System.out.println(dataTool.bytes2hex(bytes));

        ParamDownloadResp h=new ParamDownloadResp();
        h.decoded(bytes);
        System.out.println(h.toString());
        assert (h.getStatus().equals((byte)1));

    }

    @Test
    public void test_WarningMeaasge(){
        WarningMessage warningMessage=new WarningMessage();
        warningMessage.setSendingTime(1443151834);
        warningMessage.setEventId(1443151834);
        warningMessage.setAgentNum((byte) 3);

        warningMessage.setAlarmDeviceName(dataTool.getLengthBytesString("中国ABC", 100));
        warningMessage.setAlarmTitle(dataTool.getLengthBytesString("TITLE", 200));
        warningMessage.setAlarmContent(dataTool.getLengthBytesString("CCC",500));

        warningMessage.setAlarmLevel((byte)1);
        byte[] bytes=warningMessage.encoded();
        System.out.println(dataTool.bytes2hex(bytes));
        WarningMessage h=new WarningMessage();
        h.decoded(bytes);
        System.out.println(h.toString());
        assert (h.getAgentNum().equals((byte)3));
    }
}
