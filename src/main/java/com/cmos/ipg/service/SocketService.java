package com.cmos.ipg.service;

import com.cmos.ipg.bean.HeartBeatReq;
import com.cmos.ipg.bean.HeartBeatResp;
import com.cmos.ipg.utils.DataTool;
import io.netty.buffer.ByteBuf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;

/**
 * Created by jackl on 2016/4/29.
 */
@Component
public class SocketService {
    @Autowired
    DataTool dataTool;


    /**
     *
     * @param reqString 心跳请求hex
     * @return 心跳响应hex
     */
    public String getHeartbeatResp(String reqString){
        //根据心跳请求的16进制字符串，生成响应的16进制字符串
        ByteBuf bb=dataTool.getByteBuf(reqString);
        byte[] reqBytes=dataTool.getBytesFromByteBuf(bb);
        HeartBeatReq req=new HeartBeatReq();
        req.decoded(reqBytes);
        HeartBeatResp resp=new HeartBeatResp();
        resp.setSendingTime(dataTool.getCurrentSeconds());
        resp.setEventId(req.getEventId());
        resp.setAgentNum(req.getAgentNum());
        resp.setStatus((byte) 1);
        byte[] respBytes=resp.encoded();
        String respStr=dataTool.bytes2hex(respBytes);
        return respStr;
    }
}
