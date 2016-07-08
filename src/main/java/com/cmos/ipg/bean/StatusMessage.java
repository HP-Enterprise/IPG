package com.cmos.ipg.bean;

import io.netty.buffer.ByteBuf;

import java.io.UnsupportedEncodingException;

import static io.netty.buffer.Unpooled.buffer;

/**
 * Created by jackl on 2016/5/4.
 */
public class StatusMessage extends UpBean{

    private static int deviceNameSize=20;
    private static int deviceLocateSize=20;
    private static int deviceCodeSize=20;
    private static int deviceParaSize=100;
    private static int statusSize=10;


    private Byte packageNum;
    private String[] deviceName;//
    private String[] deviceLocate;//
    private String[] deviceCode;//
    private String[] devicePara;
    private String[] status1;//
    private String[] status2;//
    private String[] status3;//
    private String[] status4;//
    private String[] status5;//

    public StatusMessage() {
        this.setMessageType((byte)1);
        this.setmId((byte) 1);
    }

    public Byte getPackageNum() {
        return packageNum;
    }

    public void setPackageNum(Byte packageNum) {
        this.packageNum = packageNum;
    }

    public String[] getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String[] deviceName) {
        this.deviceName = deviceName;
    }

    public String[] getDeviceLocate() {
        return deviceLocate;
    }

    public void setDeviceLocate(String[] deviceLocate) {
        this.deviceLocate = deviceLocate;
    }

    public String[] getDevicePara() {
        return devicePara;
    }

    public void setDevicePara(String[] devicePara) {
        this.devicePara = devicePara;
    }

    public String[] getStatus1() {
        return status1;
    }

    public void setStatus1(String[] status1) {
        this.status1 = status1;
    }

    public String[] getStatus2() {
        return status2;
    }

    public void setStatus2(String[] status2) {
        this.status2 = status2;
    }

    public String[] getStatus3() {
        return status3;
    }

    public void setStatus3(String[] status3) {
        this.status3 = status3;
    }

    public String[] getStatus4() {
        return status4;
    }

    public void setStatus4(String[] status4) {
        this.status4 = status4;
    }

    public String[] getStatus5() {
        return status5;
    }

    public void setStatus5(String[] status5) {
        this.status5 = status5;
    }

    @Override
    public void decoded(byte[] data){
        try{
        ByteBuf bb = buffer(BUFFER_SIZE);
        bb.writeBytes(data);
        this.setStartCode(bb.readShort());
        this.setMessageSize(bb.readShort());
        this.setMessageType(bb.readByte());
        this.setmId(bb.readByte());
        this.setSendingTime(bb.readInt());
        this.setEventId(bb.readInt());
        this.setAgentNum(bb.readByte());
        this.setPackageNum(bb.readByte());
        byte[] parkCodeBytes = new byte[this.getParkCodeSize()];
        bb.readBytes(parkCodeBytes);
        String parkCode = new String(parkCodeBytes,"UTF-8").trim();
        this.setParkCode(parkCode);
        int _packageNum=this.getPackageNum();

        deviceName = new String[_packageNum];
        deviceCode = new String[_packageNum];
        deviceLocate = new String[_packageNum];
        devicePara=new String[_packageNum];
        status1 = new String[_packageNum];
        status2 = new String[_packageNum];
        status3 = new String[_packageNum];
        status4 = new String[_packageNum];
        status5 = new String[_packageNum];

        for (int i = 0; i <_packageNum ; i++) {
            byte[] deviceNameBytes = new byte[deviceNameSize];
            bb.readBytes(deviceNameBytes);
            deviceName[i] = new String(deviceNameBytes,"UTF-8").trim();
            byte[] deviceCodeBytes = new byte[deviceCodeSize];
            bb.readBytes(deviceCodeBytes);
            deviceCode[i] = new String(deviceCodeBytes,"UTF-8").trim();
            byte[] deviceLocateBytes = new byte[deviceLocateSize];
            bb.readBytes(deviceLocateBytes);
            deviceLocate[i] = new String(deviceLocateBytes,"UTF-8").trim();
            byte[] deviceParaBytes = new byte[deviceParaSize];
            bb.readBytes(deviceParaBytes);
            devicePara[i] = new String(deviceParaBytes,"UTF-8").trim();
            byte[] statusBytes = new byte[statusSize];
            bb.readBytes(statusBytes);
            status1[i] = new String(statusBytes,"UTF-8").trim();
            bb.readBytes(statusBytes);
            status2[i] =  new String(statusBytes,"UTF-8").trim();
            bb.readBytes(statusBytes);
            status3[i] =  new String(statusBytes,"UTF-8").trim();
            bb.readBytes(statusBytes);
            status4[i] =  new String(statusBytes,"UTF-8").trim();
            bb.readBytes(statusBytes);
            status5[i] =  new String(statusBytes,"UTF-8").trim();
        }
        this.setCheckSum(bb.readByte());
        }catch (UnsupportedEncodingException e){e.printStackTrace();}
    }

    @Override
    public byte[] encoded(){
        ByteBuf bb = buffer(BUFFER_SIZE);
        try{
        bb.writeShort(this.getStartCode());
        bb.markWriterIndex();
        bb.writeShort(0);//预先写入length=0占位
        bb.writeByte(this.getMessageType());//
        bb.writeByte(this.getmId());
        bb.writeInt(this.getSendingTime());//
        bb.writeInt(this.getEventId());
        bb.writeByte(this.getAgentNum());//
        bb.writeByte(this.getPackageNum());
        for (int i = 0; i <this.getPackageNum() ; i++) {
            bb.writeBytes(dataTool.getLengthBytesString(deviceName[i], deviceNameSize).getBytes("UTF-8"));
            bb.writeBytes(dataTool.getLengthBytesString(deviceCode[i], deviceCodeSize).getBytes("UTF-8"));
            bb.writeBytes(dataTool.getLengthBytesString(deviceLocate[i], deviceLocateSize).getBytes("UTF-8"));
            bb.writeBytes(dataTool.getLengthBytesString(devicePara[i], deviceParaSize).getBytes("UTF-8"));
            bb.writeBytes(dataTool.getLengthBytesString(status1[i], statusSize).getBytes("UTF-8"));
            bb.writeBytes(dataTool.getLengthBytesString(status2[i], statusSize).getBytes("UTF-8"));
            bb.writeBytes(dataTool.getLengthBytesString(status3[i], statusSize).getBytes("UTF-8"));
            bb.writeBytes(dataTool.getLengthBytesString(status4[i], statusSize).getBytes("UTF-8"));
            bb.writeBytes(dataTool.getLengthBytesString(status5[i], statusSize).getBytes("UTF-8"));
        }
        //回写length段
        int index=bb.writerIndex();
        bb.resetWriterIndex();
        int length=index+1;//+checksum 通过顺序写index可以计算得到length
        this.setMessageSize((short) length);
        bb.writeShort(this.getMessageSize());
        bb.writerIndex(index);
        //写完后指针复位
        ByteBuf _copy=bb.copy();
        byte[] tmp=dataTool.getBytesFromByteBuf(_copy);
        this.setCheckSum(dataTool.getCheckSum(tmp));
        bb.writeByte(this.getCheckSum());//
        }catch (UnsupportedEncodingException e){e.printStackTrace();}
        return dataTool.getBytesFromByteBuf(bb);
    }

    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("------------" + this.getClass().toString() + "------------").append("\n");
        sb.append("       StartCode:").append(this.getStartCode()).append("\n");
        sb.append(     "MessageSize:").append(this.getMessageSize()).append("\n");
        sb.append("     MessageType:").append(this.getMessageType()).append("\n");
        sb.append("             Mid:").append(this.getmId()).append("\n");
        sb.append("     SendingTime:").append(this.getSendingTime()).append("\n");
        sb.append("         EventId:").append(this.getEventId()).append("\n");
        sb.append("        AgentNum:").append(this.getAgentNum()).append("\n");

        sb.append("        CheckSum:").append(this.getCheckSum()).append("\n");
        sb.append("------------"+this.getClass().toString()+"------------").append("\n");
        return sb.toString();
    }

    public String[] getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String[] deviceCode) {
        this.deviceCode = deviceCode;
    }
}
