package com.cmos.ipg;


import com.cmos.ipg.utils.DataTool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.SocketUtils;

/**
 * Created by luj on 2015/9/28.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class DataToolTest {
    @Autowired
    DataTool dataTool;
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test_getLengthString(){
        String str="好好学习";
        System.out.println(">>>>>|"+dataTool.getLengthString(str,8)+"|");
    }

    @Test
    public void test_encodingString(){
        String str="中国ABC";
        byte[] bytes1=str.getBytes();
        System.out.println("default>>>>"+dataTool.bytes2hex(bytes1));
        try{
            byte[] bytes2=str.getBytes("GBK");
            System.out.println("gbk    >>>>"+dataTool.bytes2hex(bytes2));
            String gbk=new String(bytes2,"GBK");
            System.out.println(gbk);
        }catch (Exception e){e.printStackTrace();}
        try{
            byte[] bytes3=str.getBytes("UTF-8");
            System.out.println("utf-8  >>>>"+dataTool.bytes2hex(bytes3));
            String utf=new String(bytes3,"UTF-8");
            System.out.println(utf);
        }catch (Exception e){e.printStackTrace();}

    }

}
