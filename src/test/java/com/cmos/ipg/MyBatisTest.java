package com.cmos.ipg;

import com.cmos.ipg.entity.Data;
import com.cmos.ipg.entity.User;
import com.cmos.ipg.mapper.DataMapper;
import com.cmos.ipg.mapper.UserMapper;
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
    UserMapper userMapper;
    @Autowired
    DataMapper dataMapper;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Transactional
    @Rollback
    @Test
    public void test_getUser(){
        User u=new User();
        u.setUsername("aaa");
        u.setPassword("666666");
        userMapper.addUser(u);
        System.out.println(userMapper.findByName("jack"));
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
