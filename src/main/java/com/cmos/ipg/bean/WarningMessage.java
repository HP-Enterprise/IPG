package com.cmos.ipg.bean;

import io.netty.buffer.ByteBuf;

import java.io.UnsupportedEncodingException;

import static io.netty.buffer.Unpooled.buffer;

/**
 * Created by jackl on 2016/5/4.
 */
public class WarningMessage extends UpBean{
    private static int alarmDeviceNameSize=100;
    private static int alarmTitleSize=200;
    private static int alarmContentSize=500;

    private String alarmDeviceName;//100/4
    private String alarmTitle;//200/4
    private String alarmContent;//500/4
    private Byte alarmLevel;//告警级别 1 严重 2 重要 3 一般  4通知

    public WarningMessage() {
        this.setMessageType((byte)2);
        this.setmId((byte) 1);
    }

    public String getAlarmDeviceName() {
        return alarmDeviceName;
    }

    public void setAlarmDeviceName(String alarmDeviceName) {
        this.alarmDeviceName = alarmDeviceName;
    }

    public String getAlarmTitle() {
        return alarmTitle;
    }

    public void setAlarmTitle(String alarmTitle) {
        this.alarmTitle = alarmTitle;
    }

    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

    public Byte getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(Byte alarmLevel) {
        this.alarmLevel = alarmLevel;
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

        byte[] alarmDeviceNameBytes = new byte[alarmDeviceNameSize];
        bb.readBytes(alarmDeviceNameBytes);
        this.setAlarmDeviceName(new String(alarmDeviceNameBytes,"UTF-8").trim());

        byte[] alarmTitleBytes = new byte[alarmTitleSize];
        bb.readBytes(alarmTitleBytes);
        this.setAlarmTitle(new String(alarmTitleBytes,"UTF-8").trim());

        byte[] alarmContentBytes = new byte[alarmContentSize];
        bb.readBytes(alarmContentBytes);
        this.setAlarmContent(new String(alarmContentBytes,"UTF-8").trim());
        this.setAlarmLevel(bb.readByte());
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

            bb.writeBytes(dataTool.getLengthBytesString(this.getAlarmDeviceName(), alarmDeviceNameSize).getBytes("UTF-8"));
            bb.writeBytes(dataTool.getLengthBytesString(this.getAlarmTitle(), alarmTitleSize).getBytes("UTF-8"));
            bb.writeBytes(dataTool.getLengthBytesString(this.getAlarmContent(), alarmContentSize).getBytes("UTF-8"));

            bb.writeByte(alarmLevel);
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
        sb.append(" AlarmDeviceName:").append(this.getAlarmDeviceName()).append("\n");
        sb.append("      AlarmTitle:").append(this.getAlarmTitle()).append("\n");
        sb.append("    AlarmContent:").append(this.getAlarmContent()).append("\n");
        sb.append("      AlarmLevel:").append(this.getAlarmLevel()).append("\n");
        sb.append("        CheckSum:").append(this.getCheckSum()).append("\n");
        sb.append("------------"+this.getClass().toString()+"------------").append("\n");
        return sb.toString();
    }
}
