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
    public void test_WarningMessage(){
        WarningMessage warningMessage=new WarningMessage();
        warningMessage.setSendingTime(1443151834);
        warningMessage.setEventId(1443151834);
        warningMessage.setAgentNum((byte) 3);

        warningMessage.setAlarmDeviceName("中国ABC");
        warningMessage.setAlarmTitle("TITLE");
        warningMessage.setAlarmContent("CCC");

        warningMessage.setAlarmLevel((byte) 1);
        byte[] bytes=warningMessage.encoded();
        System.out.println(dataTool.bytes2hex(bytes));
        WarningMessage h=new WarningMessage();
        h.decoded(bytes);
        System.out.println(h.toString());
        assert (h.getAgentNum().equals((byte)3));
    }

    @Test
    public void test_StatusMessage(){
        StatusMessage statusMessage=new StatusMessage();
        statusMessage.setSendingTime(1443151834);
        statusMessage.setEventId(1443151834);
        statusMessage.setAgentNum((byte) 3);
        statusMessage.setPackageNum((byte)3);
        String[] deviceName = new String[statusMessage.getPackageNum()];
        String[] deviceLocate = new String[statusMessage.getPackageNum()];
        String[] devicePara = new String[statusMessage.getPackageNum()];
        Integer[] status1 = new Integer[statusMessage.getPackageNum()];
        Integer[] status2 = new Integer[statusMessage.getPackageNum()];
        Integer[] status3 = new Integer[statusMessage.getPackageNum()];
        Integer[] status4 = new Integer[statusMessage.getPackageNum()];
        Integer[] status5 = new Integer[statusMessage.getPackageNum()];
        for (int i = 0; i <statusMessage.getPackageNum() ; i++) {
            deviceName[i]="设备名称A"+i;
            deviceLocate[i]="设备位置B"+i;
            devicePara[i]="参数C"+i;
            status1[i]=i*1;
            status2[i]=i*2;
            status3[i]=i*3;
            status4[i]=i*4;
            status5[i]=i*5;
        }
        statusMessage.setDeviceName(deviceName);
        statusMessage.setDeviceLocate(deviceLocate);
        statusMessage.setDevicePara(devicePara);
        statusMessage.setStatus1(status1);
        statusMessage.setStatus2(status2);
        statusMessage.setStatus3(status3);
        statusMessage.setStatus4(status4);
        statusMessage.setStatus5(status5);

        byte[] bytes=statusMessage.encoded();
        System.out.println(dataTool.bytes2hex(bytes));
        StatusMessage h=new StatusMessage();
        h.decoded(bytes);
        System.out.println(h.toString());
        assert (h.getAgentNum().equals((byte)3));
    }

    @Test
    public void test_CommandReq(){
        CommandReq commandReq=new CommandReq();
        commandReq.setSendingTime(1443151834);
        commandReq.setEventId(1443151834);
        commandReq.setAgentNum((byte) 3);
        commandReq.setOrderType("控制ABC");
        commandReq.setOrderPara("abcdef");

        byte[] bytes=commandReq.encoded();
        System.out.println(dataTool.bytes2hex(bytes));
        CommandReq h=new CommandReq();
        h.decoded(bytes);
        System.out.println(h.toString());
        assert (h.getAgentNum().equals((byte)3));
    }

    @Test
    public void test_CommandResp(){
        CommandResp commandResp=new CommandResp();
        commandResp.setSendingTime(1443151834);
        commandResp.setEventId(1443151834);
        commandResp.setAgentNum((byte) 3);
        commandResp.setStatus((byte) 1);
        byte[] bytes=commandResp.encoded();
        System.out.println(dataTool.bytes2hex(bytes));
        CommandResp h=new CommandResp();
        h.decoded(bytes);
        System.out.println(h.toString());
        assert (h.getAgentNum().equals((byte)3));
    }
}
