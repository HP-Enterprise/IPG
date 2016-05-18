package com.cmos.ipg.acquire;

import com.cmos.ipg.bean.CommandReq;
import com.cmos.ipg.entity.Command;
import com.cmos.ipg.service.SocketService;
import com.cmos.ipg.utils.DataTool;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jackl on 2016/5/18.
 */
public class NettySender extends Thread{

    private Logger _logger;

    private DataTool dataTool;

    private ConcurrentHashMap<String,Channel> channels;
    private  SocketService socketService;
    public NettySender(ConcurrentHashMap<String,Channel> cs,SocketService socketService,DataTool dt){
        this.channels=cs;
        this.socketService=socketService;
        this.dataTool=dt;
        this._logger = LoggerFactory.getLogger(NettySender.class);
    }
    public  synchronized void run()
    {

        while (true){
            try{
                Thread.sleep(1000);//开发调试用
            }catch (InterruptedException e){e.printStackTrace(); }

            //_logger.info("Connection count>>:" + channels.keySet().size()+"|Thread count>>:" + maps.size());
            //读取数据库中所有的命令集合
           Command command =socketService.loadOneCommand();
           if(command!=null){
               System.out.println(command.getParam());
               SendMessage(command);
           }
        }
    }

    public void SendMessage(Command command){
        //将对应的十六进制字符串发送给客户端
       if(command==null){
         return;
       }
        _logger.info("start send command");
        String ip=socketService.getIpFromCommand(command);//从数据库得到目标ip
        Channel ch=channels.get(ip);
        if(ch!=null){
            CommandReq commandReq=new CommandReq();
            commandReq.setSendingTime(dataTool.getCurrentSeconds());
            commandReq.setEventId(dataTool.getCurrentSeconds());
            commandReq.setAgentNum((byte) 0);
            commandReq.setOrderType(command.getAction());
            commandReq.setOrderPara(command.getParam());
            byte[] bytes=commandReq.encoded();
            ByteBuf buf=dataTool.getByteBuf(dataTool.bytes2hex(bytes));
            ch.writeAndFlush(buf);
            command.setCommandStatus((short)1);
            socketService.updateCommand(command);
         }else{
            command.setCommandStatus((short)0);
            socketService.updateCommand(command);
            _logger.info("Connection is Dead:"+ip);
         }
    }

}
