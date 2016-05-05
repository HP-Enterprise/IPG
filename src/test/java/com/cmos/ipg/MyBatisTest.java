package com.cmos.ipg;

import com.cmos.ipg.entity.*;
import com.cmos.ipg.mapper.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;

/**
 * Created by jackl on 2016/4/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class MyBatisTest {
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    DataMapper dataMapper;
    @Autowired
    DeviceStatusMapper deviceStatusMapper;
    @Autowired
    DeviceStatusHistoryMapper deviceStatusHistoryMapper;
    @Autowired
    AgentMapper agentMapper;
    @Autowired
    AlarmMapper alarmMapper;
    @Autowired
    AlarmHistoryMapper alarmHistoryMapper;


    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Transactional
    @Rollback
    @Test
    public void test_alarmHis(){
        AlarmHistory alarmHistory=new AlarmHistory();
        alarmHistory.setDeviceId(1);
        alarmHistory.setAlarmDeviceName("A1");
        alarmHistory.setAlarmTitle("tlh1");
        alarmHistory.setAlarmContent("2000");
        alarmHistory.setAlarmLevel(1);
        alarmHistory.setAlarmDate(new Date());
        alarmHistoryMapper.save(alarmHistory);
        AlarmHistory f=alarmHistoryMapper.findById(1);
        assert (f.getAlarmContent().equals("2000"));
    }

    @Transactional
    @Rollback
    @Test
    public void test_alarm(){
        Alarm alarm=new Alarm();
        alarm.setDeviceId(1);
        alarm.setAlarmDeviceName("A1");
        alarm.setAlarmTitle("tl1");
        alarm.setAlarmContent("1000");
        alarm.setAlarmLevel(1);
        alarm.setAlarmDate(new Date());
        alarmMapper.save(alarm);
        Alarm f=alarmMapper.findById(1);
        assert (f.getAlarmContent().equals("1000"));
    }

    @Transactional
    @Rollback
    @Test
    public void test_agent(){
        Agent agent=new Agent();
        agent.setAgentName("AG1");
        agent.setIp("192.168.2.22");
        agent.setPort("9000");
        agent.setContable("1000");
        agentMapper.save(agent);
        Agent f=agentMapper.findById(1);
        assert (f.getContable().equals("1000"));
    }

    @Transactional
    @Rollback
    @Test
    public void test_deviceStatusHis(){
        DeviceStatusHistory deviceStatusHistory=new DeviceStatusHistory();
        deviceStatusHistory.setDeviceId(1);
        deviceStatusHistory.setDeviceParaName("vol");
        deviceStatusHistory.setDeviceParaValue("100");
        deviceStatusHistory.setCollectDate(new Date());
        deviceStatusHistoryMapper.save(deviceStatusHistory);
        DeviceStatusHistory f=deviceStatusHistoryMapper.findById(1);
        System.out.println(f.getCollectDate().toString());
        assert (f.getDeviceId()==1);
    }

    @Transactional
    @Rollback
    @Test
    public void test_deviceStatus(){
        DeviceStatus deviceStatus=new DeviceStatus();
        deviceStatus.setDeviceId(1);
        deviceStatus.setDeviceParaName("vol");
        deviceStatus.setDeviceParaValue("100");
        deviceStatusMapper.save(deviceStatus);
        DeviceStatus f=deviceStatusMapper.findById(1);
        assert (f.getDeviceId()==1);
    }

    @Transactional
    @Rollback
    @Test
    public void test_device(){
        Device device=new Device();
        device.setDeviceSn("abcdef123456");
        device.setDeviceType(1);
        device.setDeviceLocate("A1");
        device.setDeviceName("设备A");
        deviceMapper.save(device);
        Device f=deviceMapper.findById(1);
        assert (f.getDeviceLocate().equals("A1"));
    }

    @Transactional
    @Rollback
    @Test
    public void test_getData(){
        Data d=new Data();
        d.setClient("127.0.0.1");
        d.setBytes("23 23");
        dataMapper.save(d);

    }

}
