package com.cmos.ipg.acquire;


import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;


/**
 * Created by jackl on 2016/4/27.
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter { // (1)
    private ConcurrentHashMap<String,Channel> channels;
    private Logger _logger;
    private ScheduledExecutorService scheduledService;
    private int maxDistance;

    public NettyServerHandler(ConcurrentHashMap<String, Channel> cs, ScheduledExecutorService scheduledService){
        this.channels=cs;
        this.scheduledService=scheduledService;
        this._logger = LoggerFactory.getLogger(NettyServerHandler.class);
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        _logger.info("read something");
        Channel ch=ctx.channel();
        ByteBuf m = (ByteBuf) msg;
        String chKey;
        String respStr;
        ByteBuf buf;
        //将缓冲区的数据读出到byte[]
        ch.writeAndFlush(msg);//心跳流程直接回消息
         }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx){
        Channel ch=ctx.channel();
        _logger.info("Register" + ch.remoteAddress());
    }
    public void channelUnregistered(ChannelHandlerContext ctx){
        Channel ch=ctx.channel();
        _logger.info("UnRegister" + ch.remoteAddress());
        //连接断开 从map移除连接

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