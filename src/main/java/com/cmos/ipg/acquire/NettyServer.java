package com.cmos.ipg.acquire;

/**
 * Created by luj on 2016/4/27.
 */


import com.cmos.ipg.mapper.ClientLogMapper;
import com.cmos.ipg.mapper.DataMapper;
import com.cmos.ipg.utils.DataTool;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by jackl on 2016/4/27.
 */
public class NettyServer {

    private int port;
    private ConcurrentHashMap<String,Channel> channels;
    private Logger _logger;
    private  ScheduledExecutorService scheduledService;
    private DataMapper dataMapper;
    private DataTool dataTool;
    private ClientLogMapper clientLogMapper;
    public NettyServer(ConcurrentHashMap<String, Channel> cs, int port, ScheduledExecutorService scheduledService,DataMapper dataMapper,ClientLogMapper clientLogMapper,DataTool dataTool) {
        this.dataMapper=dataMapper;
        this.clientLogMapper=clientLogMapper;
        this.dataTool=dataTool;
        this.channels=cs;
        this.port = port;
        this.scheduledService=scheduledService;
        this._logger = LoggerFactory.getLogger(NettyServer.class);
    }
    public  void run()  {
        try{
            EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                ServerBootstrap b = new ServerBootstrap(); // (2)
                b.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class) // (3)
                        .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                //ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 2, 2, 2, 0));
                                ch.pipeline().addLast(new NettyServerHandler(channels,  scheduledService,dataMapper,clientLogMapper,dataTool));
                             }
                        })
                        .option(ChannelOption.SO_BACKLOG, 1024)          // (5)
                        .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

                // Bind and start to accept incoming connections.
                ChannelFuture f = b.bind(port).sync(); // (7)

                // Wait until the server socket is closed.
                // In this example, this does not happen, but you can do that to gracefully
                // shut down your server.
                f.channel().closeFuture().sync();
            } finally {
                workerGroup.shutdownGracefully();
                bossGroup.shutdownGracefully();
            }

        }catch (Exception e){e.printStackTrace();_logger.info("exception:"+e);
        }
    }


}