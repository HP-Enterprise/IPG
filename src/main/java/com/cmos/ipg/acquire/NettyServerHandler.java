package com.cmos.ipg.acquire;


import com.cmos.ipg.entity.ClientLog;
import com.cmos.ipg.entity.Data;
import com.cmos.ipg.mapper.AgentMapper;
import com.cmos.ipg.mapper.ClientLogMapper;
import com.cmos.ipg.mapper.DataMapper;
import com.cmos.ipg.service.MQService;
import com.cmos.ipg.service.SocketService;
import com.cmos.ipg.utils.ByteUtil;
import com.cmos.ipg.utils.DataTool;
//import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;


/**
 * Created by jackl on 2016/4/27.
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter { // (1)
    private ConcurrentHashMap<String,Channel> channels;
    private Logger _logger;
    private ScheduledExecutorService scheduledService;
    private DataMapper dataMapper;
    private AgentMapper agentMapper;
    private DataTool dataTool;
    private SocketService socketService;
    private ClientLogMapper clientLogMapper;
    public NettyServerHandler(ConcurrentHashMap<String, Channel> cs, ScheduledExecutorService scheduledService,DataMapper dataMapper,ClientLogMapper clientLogMapper,AgentMapper agentMapper,SocketService socketService,DataTool dataTool){
        this.dataMapper=dataMapper;
        this.clientLogMapper=clientLogMapper;
        this.agentMapper=agentMapper;
        this.socketService=socketService;
        this.dataTool=dataTool;
        this.channels=cs;
        this.scheduledService=scheduledService;
        this._logger = LoggerFactory.getLogger(NettyServerHandler.class);
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        Channel ch=ctx.channel();
        ByteBuf m = (ByteBuf) msg;
        byte[] receiveData=dataTool.getBytesFromByteBuf(m);
        String receiveDataHexString=dataTool.bytes2hex(receiveData);
        String respStr;
        ByteBuf buf;
        //将缓冲区的数据读出到byte[]
        _logger.info("Receive date from " + ch.remoteAddress() + ">>>:" + receiveDataHexString);
        _logger.info("Receive date from " + ch.remoteAddress() + ">>>:" + ByteUtil.decode(receiveDataHexString));
        InetSocketAddress socketAddress=(InetSocketAddress)ch.remoteAddress();
        String ip=socketAddress.getAddress().getHostAddress();
        int port = socketAddress.getPort();
       // System.out.println(socketAddress.getAddress().getHostAddress()+":"+socketAddress.getPort());
        if(!dataTool.checkByteArray(receiveData)) {
            _logger.info(">>>>>bytes data is invalid,we will not handle them");
        }else{
            byte dataType=dataTool.getApplicationType(receiveData);
            switch(dataType){
                case 0x01://A
                    _logger.info("StatusMessage request");
                    int result_s=socketService.handleStatusMessage(receiveDataHexString,ip,port);
                    break;
                case 0x02://B
                    _logger.info("WarningMessage request");
                    int result_w=socketService.handleWarningMessage(receiveDataHexString,ip,port);
                    break;
                case 0x03://C
                   _logger.info("Heartbeat request");
                    respStr=socketService.getHeartbeatResp(receiveDataHexString);
                    buf=dataTool.getByteBuf(respStr);
                    ch.writeAndFlush(buf);//心跳流程直接回消息
                    break;
                case 0x04://D
                    _logger.info("ParamDownload request");
                    respStr=socketService.getParamDownloadResp(ch, receiveDataHexString);
                    buf=dataTool.getByteBuf(respStr);
                    ch.writeAndFlush(buf);//
                    break;
                case 0x05://D
                    _logger.info("Command response");
                    socketService.handleCommandResp(receiveDataHexString, ip);
                    break;
                default:
                    _logger.info(">>unknown request ,log to log" + receiveDataHexString);
                     break;

            }

        }

        Data d=new Data();
        d.setClient( ch.remoteAddress().toString());
        d.setBytes(receiveDataHexString);
        d.setActionDate(new Date());
        dataMapper.save(d);
         }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
       Channel ch=ctx.channel();
        InetSocketAddress socketAddress=(InetSocketAddress)ch.remoteAddress();
        String ip=socketAddress.getAddress().getHostAddress();
        int port = socketAddress.getPort();
        _logger.info("Register" + ch.remoteAddress());
        boolean verifyResult=socketService.verifyAgent(ch);
        if(!verifyResult){
            _logger.info("Agent from " + ch.remoteAddress()+" is not in server white list!close connection!");
            ch.close();
        }else{
            channels.put(ip+port, ch);// save this connection
        }
        //修改agent的在线状态[上线]
        agentMapper.update((short) 1,ip,port);
        //记录客户端的连接信息
        ClientLog c=new ClientLog();
        c.setClient(ch.remoteAddress().toString());
        c.setAction("建立连接:"+verifyResult);
        c.setActionDate(new Date());
        clientLogMapper.save(c);
    }
    public void channelUnregistered(ChannelHandlerContext ctx){
        Channel ch=ctx.channel();
        InetSocketAddress socketAddress=(InetSocketAddress)ch.remoteAddress();
        String ip=socketAddress.getAddress().getHostAddress();
        int port = socketAddress.getPort();
        _logger.info("UnRegister" + ch.remoteAddress());
        //连接断开 从map移除连接
        channels.remove(ip+port);

        //修改agent的在线状态[下线]
        agentMapper.update((short) 0,ip,port);
        //记录客户端的连接信息
        ClientLog c=new ClientLog();
        c.setClient(ch.remoteAddress().toString());
        c.setAction("断开连接");
        c.setActionDate(new Date());
        clientLogMapper.save(c);

       }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        Channel ch=ctx.channel();
        _logger.info("exceptionCaught" + ch.remoteAddress());
        cause.printStackTrace();
        ctx.close();
    }

}