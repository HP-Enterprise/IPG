package com.cmos.ipg.bean;

import com.cmos.ipg.utils.DataTool;
import io.netty.buffer.ByteBuf;

import static io.netty.buffer.Unpooled.buffer;

/**
 * Created by jackl on 2016/4/29.
 */
public class HeartBeatResp extends DownBean{
    private Byte status;

    public static final int BUFFER_SIZE = 1024;
    public DataTool dataTool = new DataTool();
    public HeartBeatResp(){
        this.setMessageType((byte)3);
        this.setmId((byte) 2);
    }
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

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
        this.setStatus(bb.readByte());
        this.setCheckSum(bb.readByte());
    }


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
        bb.writeByte(this.getStatus());//
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

    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("------------"+this.getClass().toString()+"------------").append("\n");
        sb.append("  StartCode:").append(this.getStartCode()).append("\n");
        sb.append("MessageSize:").append(this.getMessageSize()).append("\n");
        sb.append("MessageType:").append(this.getMessageType()).append("\n");
        sb.append("        Mid:").append(this.getmId()).append("\n");
        sb.append("SendingTime:").append(this.getSendingTime()).append("\n");
        sb.append("    EventId:").append(this.getEventId()).append("\n");
        sb.append("   AgentNum:").append(this.getAgentNum()).append("\n");
        sb.append("     Status:").append(this.getStatus()).append("\n");
        sb.append("   CheckSum:").append(this.getCheckSum()).append("\n");
        sb.append("------------"+this.getClass().toString()+"------------").append("\n");
        return sb.toString();
    }
}