package com.cmos.ipg.service;

import com.cmos.core.bean.InputObject;
import com.cmos.core.bean.OutputObject;
import com.cmos.ipg.bean.*;
import com.cmos.ipg.dubbo.IControlIPGService;
import com.cmos.ipg.dubbo.ReceiveAlarmService;
import com.cmos.ipg.entity.*;
import com.cmos.ipg.mapper.*;
import com.cmos.ipg.utils.DataTool;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.management.CompilationMXBean;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.*;
import java.util.HashMap;
import java.util.List;


/**
 * Created by jackl on 2016/4/29.
 */
@Component
public class SocketService {
    @Autowired
    DataTool dataTool;
    @Autowired
    MQService mqService;
    @Autowired
    DeviceStatusMapper deviceStatusMapper;
    @Autowired
    DeviceStatusHistoryMapper deviceStatusHistoryMapper;
    @Autowired
    AlarmMapper alarmMapper;
    @Autowired
    AlarmHistoryMapper alarmHistoryMapper;
    @Autowired
    AgentMapper agentMapper;
    @Autowired
    CommandMapper commandMapper;
    //@Autowired
    ReceiveAlarmService receiveAlarmService;
    @Autowired
    IControlIPGService controlService;
    @Autowired
    DeviceRecordMapper deviceRecordMapper;
    @Autowired
    DeviceRecordDetailMapper deviceRecordDetailMapper;
    @Autowired
    EnergyRecordMapper energyRecordMapper;
    @Autowired
    EnergyRecordDetailMapper energyRecordDetailMapper;
    @Autowired
    private CWPCoreService cwpCoreService;
    private Logger _logger= LoggerFactory.getLogger(SocketService.class);


    public boolean verifyAgent( Channel ch){
        InetSocketAddress socketAddress=(InetSocketAddress)ch.remoteAddress();
        String ip=socketAddress.getAddress().getHostAddress();
        //根据参数下载请求的16进制字符串，生成响应的16进制字符串
        Agent agent=agentMapper.findByAgentIp(ip);
        if(agent==null){
            return false;
        }
        return true;
    }

    /**
     * 处理客户端心跳
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
        resp.setParkCode(req.getParkCode());
        resp.setStatus((byte) 1);
        byte[] respBytes=resp.encoded();
        String respStr=dataTool.bytes2hex(respBytes);
        return respStr;
    }

    /**
     * 处理参数下载请求
     * @param ch Channel
     * @param reqString 参数下载请求hex
     * @return 参数下载响应hex
     */
    public String getParamDownloadResp( Channel ch,String reqString){
        InetSocketAddress socketAddress=(InetSocketAddress)ch.remoteAddress();
       // System.out.println(socketAddress.getAddress().getHostAddress()+":"+socketAddress.getPort());
        String ip=socketAddress.getAddress().getHostAddress();
        //根据参数下载请求的16进制字符串，生成响应的16进制字符串
        Agent agent=agentMapper.findByAgentIp(ip);
        ByteBuf bb=dataTool.getByteBuf(reqString);
        byte[] reqBytes=dataTool.getBytesFromByteBuf(bb);
        ParamDownloadReq req=new ParamDownloadReq();
        req.decoded(reqBytes);
        ParamDownloadResp resp=new ParamDownloadResp();
        resp.setSendingTime(dataTool.getCurrentSeconds());
        resp.setEventId(req.getEventId());
        resp.setAgentNum(req.getAgentNum());
        //todo 从db获取配置参数
        if(agent==null){
            resp.setStatus((byte) 0);
            resp.setCollectContab(0);
            resp.setCollectProtocol((byte) 0);
        }else{
            resp.setStatus((byte) 1);
            resp.setCollectContab(agent.getContable());
            resp.setCollectProtocol((byte)agent.getConProtocol());
        }
        byte[] respBytes=resp.encoded();
        String respStr=dataTool.bytes2hex(respBytes);
        return respStr;
    }

    /**
     * 处理状态信息上报
     * @param reqString 状态信息hex
     * @param ip c端ip
     * @return 处理结果
     */
    public int handleStatusMessage(String reqString,String ip){
        //
        try{
            ByteBuf bb=dataTool.getByteBuf(reqString);
            byte[] reqBytes=dataTool.getBytesFromByteBuf(bb);
            StatusMessage req=new StatusMessage();
            req.decoded(reqBytes);
            Agent _agent=agentMapper.findByAgentIp(ip);
            if(_agent==null){
                _logger.info("save failed,from ip "+ip+" not in the db");
                return 2;//from ip not in the list
            }
            //save deviceStatusHistory
            List<String> alarmParam = new ArrayList<String>();
            //判断楼控信息或能耗信息
            Agent agt = agentMapper.findByAgentNum(req.getAgentNum());
            if(2==agt.getAgentType()){//楼控信息
            	doDeviceRecord(req);
            	int result = cwpCoreService.sendDeviceRecordMessage(reqString);
            	if(result!=0){
            		try{
            			mqService.pushToUser(0, reqString, MqConsumerService.TOPIC_STATUS, MqConsumerService.TAG_DEVICE, "");
            		}catch(Exception e){
            			e.printStackTrace();
            		}
            	}
            }else if(4==agt.getAgentType()){//能耗
            	doEnergyRecord(req);
            	int result = cwpCoreService.sendEnergyRecordMessage(reqString);
            	if(result!=0){
            		try{
            			mqService.pushToUser(0, reqString, MqConsumerService.TOPIC_STATUS, MqConsumerService.TAG_ENERGY, "");
            		}catch(Exception e){
            			e.printStackTrace();
            		}
            	}
            }else{
            	_logger.info("无法匹配代理服务类型");
            }
            int num=req.getPackageNum();
            for (int i = 0; i < num; i++) {
                //save deviceStatusHistory
                DeviceStatusHistory deviceStatusHistory=new DeviceStatusHistory();
                deviceStatusHistory.setDeviceId(-1);
                deviceStatusHistory.setDeviceName(req.getDeviceName()[i]);
                deviceStatusHistory.setDeviceCode(req.getDeviceCode()[i]);
                deviceStatusHistory.setDeviceLocation(req.getDeviceLocate()[i]);
                deviceStatusHistory.setDeviceParaName(req.getDevicePara()[i]);
                deviceStatusHistory.setDeviceParaValue(req.getStatus1()[i]);
                deviceStatusHistory.setCollectDate(dataTool.seconds2Date(req.getSendingTime()));
                deviceStatusHistoryMapper.save(deviceStatusHistory);

                //delete
                deviceStatusMapper.deleteByNameAndPara(req.getDeviceName()[i],req.getDevicePara()[i]);
                //save deviceStatusHistory
                DeviceStatus deviceStatus=new DeviceStatus();
                deviceStatus.setDeviceId(-1);
                deviceStatus.setDeviceName(req.getDeviceName()[i]);
                deviceStatus.setDeviceCode(req.getDeviceCode()[i]);
                deviceStatus.setDeviceLocation(req.getDeviceLocate()[i]);
                deviceStatus.setDeviceParaName(req.getDevicePara()[i]);
                deviceStatus.setDeviceParaValue(req.getStatus1()[i]);
                deviceStatusMapper.save(deviceStatus);
               
                //判断告警信息
                if(req.getDevicePara()[i].equals("deviceFaultAlarm")){
                        alarmParam.add("运行异常告警#0") ;
                        //AlarmHistory
                        AlarmHistory alarmHistory=new AlarmHistory();
                        alarmHistory.setDeviceId(-1);
                        alarmHistory.setAlarmDeviceName(req.getDeviceName()[0]);
                        alarmHistory.setAlarmTitle("运行异常告警");
                        alarmHistory.setAlarmContent("运行异常告警");
                        alarmHistory.setAlarmLevel(0);
                        alarmHistory.setAlarmDate(dataTool.seconds2Date(req.getSendingTime()));
                        alarmHistoryMapper.save(alarmHistory);

                        //Alarm
                        alarmMapper.deleteByName(req.getDeviceName()[0]);//delete current alarm info
                        Alarm alarm=new Alarm();
                        alarm.setDeviceId(-1);
                        alarm.setAlarmDeviceName(req.getDeviceName()[0]);
                        alarm.setAlarmTitle("运行异常告警");
                        alarm.setAlarmContent("运行异常告警");
                        alarm.setAlarmLevel(0);
                        alarm.setAlarmDate(dataTool.seconds2Date(req.getSendingTime()));
                        alarmMapper.save(alarm);
                }

            }
            String[] alarms = new String[alarmParam.size()];
            for(int i=0;i<alarmParam.size();i++){
            	alarms[i]=alarmParam.get(i).toString();
            }
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 1;//非正常返回
        }
    }

    /**
     * 本地保存能耗信息
     * @param req
     */
 	private void doEnergyRecord(StatusMessage req) {
 		 EnergyRecord energyRecord = new EnergyRecord();
      	energyRecord.setParkCode(req.getParkCode());
      	energyRecord.setDeviceSy(new Date());
      	energyRecord.setDeviceCode(req.getDeviceCode()[0]);
         energyRecord.setDeviceLocate(req.getDeviceLocate()[0]);
         energyRecord.setDeviceName(req.getDeviceName()[0]);
        // energyRecord.setDeviceType(''));
      	energyRecordMapper.save(energyRecord);
      	for (int i = 0; i < req.getPackageNum(); i++) {
      		EnergyRecordDetail energyRecordDetail = new EnergyRecordDetail();
         	energyRecordDetail.setDeviceId(energyRecord.getId());
             energyRecordDetail.setDeviceLocation(energyRecord.getDeviceLocate());
             energyRecordDetail.setDeviceName(energyRecord.getDeviceName());
             energyRecordDetail.setDeviceCode(energyRecord.getDeviceCode());
             energyRecordDetail.setDeviceParaName(req.getDevicePara()[i]);
             energyRecordDetail.setDeviceParaValue(req.getStatus1()[i]);
         	energyRecordDetailMapper.save(energyRecordDetail);
     	}
 	}
 	/**
 	 * 本地保存楼控信息
 	 * @param req
 	 */
 	private void doDeviceRecord(StatusMessage req) {
 		 DeviceRecord deviceRecord = new DeviceRecord();
     	deviceRecord.setParkCode(req.getParkCode());
     	deviceRecord.setDeviceSy(new Date());
     	deviceRecord.setDeviceCode(req.getDeviceCode()[0]);
         deviceRecord.setDeviceName(req.getDeviceName()[0]);
         deviceRecord.setDeviceLocate(req.getDeviceLocate()[0]);
         //deviceRecord.setDeviceType(req.getMessageType());
     	deviceRecordMapper.save(deviceRecord);
     	for (int i = 0; i < req.getPackageNum(); i++) {
     		DeviceRecordDetail deviceRecordDetail = new DeviceRecordDetail();
     		deviceRecordDetail.setDeviceId(deviceRecord.getId());
             deviceRecordDetail.setDeviceName(deviceRecord.getDeviceName());
             deviceRecordDetail.setDeviceCode(deviceRecord.getDeviceCode());
             deviceRecordDetail.setDeviceLocation(deviceRecord.getDeviceLocate());
             deviceRecordDetail.setDeviceParaName(req.getDevicePara()[i]);
             deviceRecordDetail.setDeviceParaValue(req.getStatus1()[i]);
         	deviceRecordDetailMapper.save(deviceRecordDetail);
     	}
 	}

	/**
     * 处理告警信息上报
     * @param reqString 告警信息hex
     * @param ip c端ip
     * @return 处理结果
     */
    public int handleWarningMessage(String reqString,String ip){
        //
        try{
            InputObject io = new InputObject();
            io.setMethod("insertDeviceAccessByAgent");
            io.setService("DeviceOpenDetailsService");
            Map map = new HashMap<String,Object>() ;

            ByteBuf bb=dataTool.getByteBuf(reqString);
            byte[] reqBytes=dataTool.getBytesFromByteBuf(bb);
            WarningMessage req=new WarningMessage();
            req.decoded(reqBytes);
            System.out.println("save to mysql>>>:");
            // todo save to db and push to mq
            Agent _agent=agentMapper.findByAgentIp(ip);
            if(_agent==null){
                _logger.info("save failed,from ip "+ip+" not in the db");
                return 2;//from ip not in the list
            }
            //AlarmHistory
            AlarmHistory alarmHistory=new AlarmHistory();
            alarmHistory.setDeviceId(-1);
            alarmHistory.setAlarmDeviceName(req.getAlarmDeviceName());
            alarmHistory.setAlarmDeviceCode(req.getAlarmDeviceCode());
            alarmHistory.setAlarmDeviceLocate(req.getAlarmDeviceLocate());
            alarmHistory.setAlarmTitle(req.getAlarmTitle());
            alarmHistory.setAlarmContent(req.getAlarmContent());
            alarmHistory.setAlarmLevel(req.getAlarmLevel());
            alarmHistory.setAlarmDate(dataTool.seconds2Date(req.getSendingTime()));
            alarmHistoryMapper.save(alarmHistory);

            //Alarm
            alarmMapper.deleteByName(req.getAlarmDeviceName());//delete current alarm info
            Alarm alarm=new Alarm();
            alarm.setDeviceId(-1);
            alarm.setAlarmDeviceName(req.getAlarmDeviceName());
            alarm.setAlarmDeviceCode(req.getAlarmDeviceCode());
            alarm.setAlarmDeviceLocate(req.getAlarmDeviceLocate());
            alarm.setAlarmTitle(req.getAlarmTitle());
            alarm.setAlarmContent(req.getAlarmContent());
            alarm.setAlarmLevel(req.getAlarmLevel());
            alarm.setAlarmDate(dataTool.seconds2Date(req.getSendingTime()));
            alarmMapper.save(alarm);
            //push message
            //String pushMsg=req.getAlarmLevel()+":"+req.getAlarmDeviceName()+","+req.getAlarmTitle()+","+req.getAlarmContent();
            //mqService.pushToUser(1,pushMsg);
            // send via Dubbo
            //如果没有Dubbo服务,请先注释掉line 42 避免Spring启动错误
            //try {
              //  System.out.println( receiveAlarmService.send(pushMsg));
            //}catch (Exception e){
              //  e.printStackTrace();
              //  this._logger.error("Dubbo Consumer filed:"+e.getMessage());
            //}
            //dubbo end

            map.put("deviceName", req.getAlarmDeviceName());
            map.put("deviceCode", req.getAlarmDeviceCode());
            map.put("deviceLoction", req.getAlarmDeviceLocate());
            map.put("paraName", req.getAlarmTitle());
            map.put("paraValue", req.getAlarmContent());
            map.put("sendTime", new Date());
            io.setParams(map);
            OutputObject oo= controlService.execute(io);
            if(!oo.getBusiCode().equals("0")) {
                return 1;
            }
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 1;//非正常返回
        }
    }

    /**
     * 处理控制指令响应
     * @param reqString 控制响应hex
     * @param ip c端ip
     * @return 处理结果
     */
    public int handleCommandResp(String reqString,String ip){
        //
        try{
            ByteBuf bb=dataTool.getByteBuf(reqString);
            byte[] reqBytes=dataTool.getBytesFromByteBuf(bb);
            CommandResp commandResp=new CommandResp();
            commandResp.decoded(reqBytes);
            _logger.info("CommandResp" + commandResp.getEventId() + "-" + commandResp.getStatus());
            Command c=commandMapper.findOneByEventId(commandResp.getEventId());
            if(c!=null){
                c.setCommandStatus(commandResp.getStatus());
                commandMapper.update(c);
            }
             return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 1;//非正常返回
        }
    }


    public Command updateCommand( Command c){
       commandMapper.update(c);
        return c;
    }
    public Command loadOneCommand(){
        Command c=commandMapper.findOne();
        if(c!=null){
            c.setCommandStatus((short)-3);//正在处理
            commandMapper.update(c);
        }
        return c;
    }

    public Agent getAgentFromCommand(Command command){

      return agentMapper.findByAgentTypeAndNum(command.getCommandType(),command.getNum());

    }
}
