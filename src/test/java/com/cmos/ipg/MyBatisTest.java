package com.cmos.ipg;

import com.cmos.ipg.entity.Data;
import com.cmos.ipg.entity.Device;
import com.cmos.ipg.mapper.DataMapper;
import com.cmos.ipg.mapper.DeviceMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }



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
