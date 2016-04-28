package com.cmos.ipg.acquire;


import com.cmos.ipg.entity.ClientLog;
import com.cmos.ipg.entity.Data;
import com.cmos.ipg.mapper.ClientLogMapper;
import com.cmos.ipg.mapper.DataMapper;
import com.cmos.ipg.utils.DataTool;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private DataTool dataTool;
    private ClientLogMapper clientLogMapper;
    public NettyServerHandler(ConcurrentHashMap<String, Channel> cs, ScheduledExecutorService scheduledService,DataMapper dataMapper,ClientLogMapper clientLogMapper,DataTool dataTool){
        this.dataMapper=dataMapper;
        this.clientLogMapper=clientLogMapper;
        this.dataTool=dataTool;
        this.channels=cs;
        this.scheduledService=scheduledService;
        this._logger = LoggerFactory.getLogger(NettyServerHandler.class);
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        _logger.info("read something");
        Channel ch=ctx.channel();
        ByteBuf m = (ByteBuf) msg;
        byte[] receiveData=dataTool.getBytesFromByteBuf(m);
        String receiveDataHexString=dataTool.bytes2hex(receiveData);
        //将缓冲区的数据读出到byte[]
        ch.writeAndFlush(dataTool.getByteBuf(receiveDataHexString));
        Data d=new Data();
        d.setClient( ch.remoteAddress().toString());
        d.setBytes(receiveDataHexString);
        d.setCreateDate(new Date());
        dataMapper.save(d);
         }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx){
        Channel ch=ctx.channel();
        _logger.info("Register" + ch.remoteAddress());
        ClientLog c=new ClientLog();
        c.setClient(ch.remoteAddress().toString());
        c.setAction("建立连接");
        c.setCreateDate(new Date());
        clientLogMapper.save(c);
    }
    public void channelUnregistered(ChannelHandlerContext ctx){
        Channel ch=ctx.channel();
        _logger.info("UnRegister" + ch.remoteAddress());
        //连接断开 从map移除连接
        ClientLog c=new ClientLog();
        c.setClient(ch.remoteAddress().toString());
        c.setAction("断开连接");
        c.setCreateDate(new Date());
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