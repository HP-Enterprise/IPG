package com.cmos.ipg.utils;

import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.*;

import static io.netty.buffer.Unpooled.buffer;

/**
 * Created by luj on 2015/9/17.
 */
@Component
public class DataTool {


    private Logger _logger = LoggerFactory.getLogger(DataTool.class);

    public  boolean checkReg(byte[] bytes){
        //校验注册数据
        return true;
    }


    public byte[] getIpBytes(String ip){
        //IP地址转换  192.168.1.1读出 00 00 C0 A8 01 01
        String[] ips=ip.split("\\.");
        byte[] bytes = new byte[]{(byte)0,(byte)0,(byte)Integer.parseInt(ips[0]),(byte)Integer.parseInt(ips[1]),(byte)Integer.parseInt(ips[2]),(byte)Integer.parseInt(ips[3])};
        return bytes;
    }

    public  String getBinaryStrFromByte(byte b)
    {
        //将byte转换层二进制字符串 (byte)170  ->> 10101010
        String result ="";
        byte a = b;
        for (int i = 0; i < 8; i++)
        {
            byte c=a;
            a=(byte)(a>>1);
            a=(byte)(a<<1);
            if(a==c){
                result="0"+result;
            }else{
                result="1"+result;
            }
            a=(byte)(a>>1);
        }
        return result;
    }

    public  byte getApplicationType(byte[] bytes){
        //返回数据包操作类型对应的byte
        byte data=0;
        if(bytes!=null){
            if(bytes.length>4) {
                data=bytes[4];
            }
        }
        return data;
    }
    public  byte getMessageId(byte[] bytes){
        //返回数据包操作类型对应的byte
        byte data=0;
        if(bytes!=null){
            if(bytes.length>10) {
                data=bytes[10];
            }
        }
        return data;
    }


    public  String bytes2hex(byte[] bArray) {
        //字节数据转16进制字符串
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return getSpaceHex(sb.toString());
    }

    public int getCurrentSeconds(){
        //返回当前时间的秒数
        int currentSeconds=Integer.valueOf(String.valueOf(new Date().getTime()/1000));
        return currentSeconds;
    }

    public Date seconds2Date(long seconds){
        //时间的秒数转换成Date
        Date d=new Date(seconds*1000L);
        return d;
    }

    public String getLengthString(String str,int length){
        //将给定字符串右补空格为定长字符串
        if(str==null){
            return str;
        }
        if(str.length()>=length){
            return str;
        }
        while (str.length()<length){
            str=str+" ";
        }
        return str;
    }

    public String getLengthBytesString(String str,int length){
        //将给定字符串右补空格为定长字符串
        if(str==null){
            return str;
        }

    if(str.getBytes().length>=length){
        return str;
    }
    while (str.getBytes().length<length){
        str=str+" ";
    }

        return str;
    }


    public  char[] getBitsFrom2Byte(byte[]  bytes){
        String a=new String(getBitsFromByte(bytes[0]))+new String(getBitsFromByte(bytes[1]));
        return a.toCharArray();
    }
    public  char[] getBitsFromInteger(int value){
        //双字节转二进制
        char[] array=new char[16];
        for (int j = 15; j >= 0; j--)
        {
            if (((1 << j) & value) != 0)
            {
                array[15-j]='1';
            }
            else
            {
                array[15-j]='0';
            }
        }
        return array;
    }
    public  char[] getBitsFromLong(long value){
        //4字节转二进制
        char[] array=new char[32];
        for (int j = 31; j >= 0; j--)
        {
            if (((1 << j) & value) != 0)
            {
                array[31-j]='1';
            }
            else
            {
                array[31-j]='0';
            }
        }
        return array;
    }

    public char[] getBitsFromShort(short a){
        //取包含8个数字的数组
        String binStr=getBinaryStrFromByte((byte)a);
        return binStr.toCharArray();
    }
    public char[] getBitsFromByte(Byte a){
        //取包含8个数字的数组
        String binStr=getBinaryStrFromByte(a);
        return binStr.toCharArray();
    }


    public  String getSpaceHex(String str){
        //将不带空格的16进制字符串加上空格
        String re="";
        String regex = "(.{2})";
        re = str.replaceAll (regex, "$1 ");
        return re;
    }
    public  ByteBuffer getByteBuffer(String str){
        //根据16进制字符串得到buffer
        ByteBuffer bb= ByteBuffer.allocate(1024);
        String[] command=str.split(" ");
        byte[] abc=new byte[command.length];
        for(int i=0;i<command.length;i++){
            abc[i]=Integer.valueOf(command[i],16).byteValue();
        }
        bb.put(abc);
        bb.flip();
        return bb;
    }
    public  ByteBuf getByteBuf(String str){
        //根据16进制字符串得到ByteBuf对象(netty)
        ByteBuf bb=buffer(1024);

        String[] command=str.split(" ");
        byte[] abc=new byte[command.length];
        for(int i=0;i<command.length;i++){
            abc[i]=Integer.valueOf(command[i],16).byteValue();
        }
        bb.writeBytes(abc);
        return bb;
    }


    public  byte[] getBytesFromByteBuffer(ByteBuffer buff){
        byte[] result = new byte[buff.remaining()];
        if (buff.remaining() > 0) {
            buff.get(result, 0, buff.remaining());
        }
        return result;
    }

    public  static byte[] getBytesFromByteBuf(ByteBuf buf){
        //基于netty
        byte[] result = new byte[buf.readableBytes()];
        buf.readBytes(result, 0, buf.readableBytes());
        return result;
    }

    public   HashMap<String,Object> getApplicationIdAndMessageIdFromDownBytes(String msg)
    {
        //解析下行数据包,提取ApplicationId和MessageId
        //eventId      :33
        //ApplicationId:9
        //MessageId    :10

        //String ApplicationId="";
        byte[] data=getBytesFromByteBuf(getByteBuf(msg));
        byte applicationId=0;
        byte messageId=0;
        int eventId=0;
        HashMap<String,Object> re=new HashMap<String ,Object>();
        if(data!=null){
            if(data.length>15) {//下行数据包最小长度16
                ByteBuffer bb= ByteBuffer.allocate(1024);
                bb.put(data);
                bb.flip();
                applicationId=bb.get(9);
                messageId=bb.get(10);
                eventId= bb.getInt(11);
            }
        }
        re.put("applicationId",applicationId);
        re.put("messageId",messageId);
        re.put("eventId",eventId);
        return re;
    }




    public   boolean checkByteArray(byte[] data)
    {
        //校验数据包是否合法
        // 包头 0X23 0X23  2个字节长度
        //包尾 将编码后的报文（ Message Header -- Application Data）进行异或操作，1个字节长度
        boolean result=false;
        if(data!=null){
            if(data.length>2) {
                if (data[0] == 0x24 && data[1] == 0x24 && checkSum(data)) {
                    result = true;
                }
            }
        }
        return result;
    }
    public  boolean checkSum(byte[] bytes){
        //将字节数组除了最后一位的部分进行异或操作，与最后一位比较
        //校验数据包尾
        //将编码后的报文（ Message Header -- Application Data）进行异或操作， 1 个字节长度
        byte sum=bytes[0];
        for(int i=1;i<bytes.length-1;i++){
            sum^=bytes[i];
        }
        _logger.info(">>checkSum:" + Integer.toHexString(sum) + "<>" + Integer.toHexString(bytes[bytes.length - 1]));
        return bytes[bytes.length-1]==sum;
    }

    public  byte getCheckSum(byte[] bytes){
        //将字节数组进行异或操作求和
        byte sum=bytes[0];
        for(int i=1;i<bytes.length;i++){
            sum^=bytes[i];
        }
        return sum;
    }

}
