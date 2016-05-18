package com.cmos.ipg.bean;

import io.netty.buffer.ByteBuf;

import java.io.UnsupportedEncodingException;

import static io.netty.buffer.Unpooled.buffer;

/**
 * Created by jackl on 2016/5/18.
 */
public class CommandReq extends DownBean{

    private static int orderTypeSize=20;
    private static int orderParaSize=100;

    private String orderType;//
    private String orderPara;//
    public CommandReq(){
        this.setMessageType((byte)5);
        this.setmId((byte) 1);
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
            byte[] orderTypeBytes = new byte[orderTypeSize];
            bb.readBytes(orderTypeBytes);
            String _orderType = new String(orderTypeBytes,"UTF-8").trim();
            this.setOrderType(_orderType);
            byte[] _orderParaBytes = new byte[orderParaSize];
            bb.readBytes(_orderParaBytes);
            String __orderPara = new String(_orderParaBytes,"UTF-8").trim();
            this.setOrderPara(__orderPara);
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
            bb.writeBytes(dataTool.getLengthBytesString(orderType, orderTypeSize).getBytes("UTF-8"));
            bb.writeBytes(dataTool.getLengthBytesString(orderPara, orderParaSize).getBytes("UTF-8"));
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


    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderPara() {
        return orderPara;
    }

    public void setOrderPara(String orderPara) {
        this.orderPara = orderPara;
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
        sb.append("       OrderType:").append(this.getOrderType()).append("\n");
        sb.append("       OrderPara:").append(this.getOrderPara()).append("\n");
        sb.append("        CheckSum:").append(this.getCheckSum()).append("\n");
        sb.append("------------"+this.getClass().toString()+"------------").append("\n");
        return sb.toString();
    }
}
