package com.cmos.ipg.bean;

import com.cmos.ipg.utils.DataTool;
import io.netty.buffer.ByteBuf;
import org.apache.log4j.Logger;

import static io.netty.buffer.Unpooled.buffer;
/**
 * Created by jackl on 2016/4/29.
 */
public class HeartBeatReq extends UpBean{
    private Logger logger=Logger.getLogger(HeartBeatReq.class);
    private Byte heartBeat;
    public static final int BUFFER_SIZE = 1024;
    private int lengthCount=0;
    public DataTool dataTool = new DataTool();
    public Byte getHeartBeat() {
        return heartBeat;
    }
    public void setHeartBeat(Byte heartBeat) {
        this.heartBeat = heartBeat;
    }

    public HeartBeatReq decoded(byte[] data){
        HeartBeatReq heartBeatReq=new HeartBeatReq();
        ByteBuf bb = buffer(BUFFER_SIZE);
        bb.writeBytes(data);
        heartBeatReq.setStartCode(bb.readShort());
        heartBeatReq.setLength(bb.readShort());
        heartBeatReq.setMessageType(bb.readByte());
        heartBeatReq.setSendingTime(bb.readInt());
        heartBeatReq.setAgentNum(bb.readByte());
        heartBeatReq.setHeartBeat(bb.readByte());
        heartBeatReq.setCheckSum(bb.readByte());
        return heartBeatReq;
    }

    public void generaData(){
        this.setCheckSum((byte)10);
    }
    public byte[] encoded(){
        generaData();
        ByteBuf bb = buffer(BUFFER_SIZE);
        int countByte = 0;//消息长度
        bb.writeShort(this.getStartCode());
        countByte=countByte+2;
        bb.markWriterIndex();
        bb.writeShort(0);//预先写入length=0占位
        bb.writeByte(this.getMessageType());//
        countByte=countByte+1;
        bb.writeInt(this.getSendingTime());//
        countByte=countByte+4;
        bb.writeByte(this.getAgentNum());//
        countByte=countByte+1;
        //回写length段
        int index=bb.writerIndex();
        bb.resetWriterIndex();
        countByte=countByte+2+1;//length+checksum
        this.setLength((short)countByte);
        bb.writeShort(this.getLength());
        bb.writerIndex(index);
        //写完后指针复位
        bb.writeByte(this.getCheckSum());//
        return dataTool.getBytesFromByteBuf(bb);
    }
}
