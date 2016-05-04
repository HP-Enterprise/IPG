package com.cmos.ipg.bean;

import io.netty.buffer.ByteBuf;

import static io.netty.buffer.Unpooled.buffer;

/**
 * Created by jackl on 2016/5/4.
 */
public class StatusMessage extends UpBean{

    private static int deviceNameSize=100;
    private static int deviceLocateSize=200;

    private Byte packageNum;
    private String[] deviceName;//
    private String[] deviceLocate;//
    private Integer[] status1;//
    private Integer[] status2;//
    private Integer[] status3;//
    private Integer[] status4;//
    private Integer[] status5;//

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

    public Integer[] getStatus1() {
        return status1;
    }

    public void setStatus1(Integer[] status1) {
        this.status1 = status1;
    }

    public Integer[] getStatus2() {
        return status2;
    }

    public void setStatus2(Integer[] status2) {
        this.status2 = status2;
    }

    public Integer[] getStatus3() {
        return status3;
    }

    public void setStatus3(Integer[] status3) {
        this.status3 = status3;
    }

    public Integer[] getStatus4() {
        return status4;
    }

    public void setStatus4(Integer[] status4) {
        this.status4 = status4;
    }

    public Integer[] getStatus5() {
        return status5;
    }

    public void setStatus5(Integer[] status5) {
        this.status5 = status5;
    }

    @Override
    public void decoded(byte[] data){
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
        int _packageNum=this.getPackageNum();

        deviceName = new String[_packageNum];
        deviceLocate = new String[_packageNum];
        status1 = new Integer[_packageNum];
        status2 = new Integer[_packageNum];
        status3 = new Integer[_packageNum];
        status4 = new Integer[_packageNum];
        status5 = new Integer[_packageNum];

        for (int i = 0; i <_packageNum ; i++) {
            byte[] deviceNameBytes = new byte[deviceNameSize];
            bb.readBytes(deviceNameBytes);
            deviceName[i] = new String(deviceNameBytes);
            byte[] deviceLocateBytes = new byte[deviceLocateSize];
            bb.readBytes(deviceLocateBytes);
            deviceLocate[i] = new String(deviceLocateBytes);
            status1[i] =  bb.readInt();
            status2[i] =  bb.readInt();
            status3[i] =  bb.readInt();
            status4[i] =  bb.readInt();
            status5[i] =  bb.readInt();
        }
        this.setCheckSum(bb.readByte());
    }

    @Override
    public byte[] encoded(){
        ByteBuf bb = buffer(BUFFER_SIZE);
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
            bb.writeBytes(dataTool.getLengthBytesString(deviceName[i], deviceNameSize).getBytes());
            bb.writeBytes(dataTool.getLengthBytesString(deviceLocate[i], deviceLocateSize).getBytes());
            bb.writeInt(status1[i]);
            bb.writeInt(status2[i]);
            bb.writeInt(status3[i]);
            bb.writeInt(status4[i]);
            bb.writeInt(status5[i]);
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
}
