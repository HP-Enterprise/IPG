package com.cmos.ipg.service;

import com.cmos.ipg.bean.*;
import com.cmos.ipg.entity.Alarm;
import com.cmos.ipg.entity.AlarmHistory;
import com.cmos.ipg.entity.DeviceStatus;
import com.cmos.ipg.entity.DeviceStatusHistory;
import com.cmos.ipg.mapper.AlarmHistoryMapper;
import com.cmos.ipg.mapper.AlarmMapper;
import com.cmos.ipg.mapper.DeviceStatusHistoryMapper;
import com.cmos.ipg.mapper.DeviceStatusMapper;
import com.cmos.ipg.utils.DataTool;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.Date;

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
        System.out.println(ch.remoteAddress());
        //根据参数下载请求的16进制字符串，生成响应的16进制字符串
        ByteBuf bb=dataTool.getByteBuf(reqString);
        byte[] reqBytes=dataTool.getBytesFromByteBuf(bb);

        ParamDownloadReq req=new ParamDownloadReq();
        req.decoded(reqBytes);
        ParamDownloadResp resp=new ParamDownloadResp();
        resp.setSendingTime(dataTool.getCurrentSeconds());
        resp.setEventId(req.getEventId());
        resp.setAgentNum(req.getAgentNum());
        resp.setStatus((byte) 1);
        //todo 从db获取配置参数
        resp.setCollectContab(15);
        resp.setCollectProtocol((byte) 2);
        byte[] respBytes=resp.encoded();
        String respStr=dataTool.bytes2hex(respBytes);
        return respStr;
    }

    /**
     * 处理状态信息上报
     * @param reqString 状态信息hex
     * @return 处理结果
     */
    public int handleStatusMessage(String reqString){
        //
        try{
            ByteBuf bb=dataTool.getByteBuf(reqString);
            byte[] reqBytes=dataTool.getBytesFromByteBuf(bb);
            StatusMessage req=new StatusMessage();
            req.decoded(reqBytes);
            System.out.println("save to mysql>>>:");
            // todo save to db and push to mq
            //save deviceStatusHistory
            int num=req.getPackageNum();
            for (int i = 0; i < num; i++) {
                //save deviceStatusHistory
                DeviceStatusHistory deviceStatusHistory=new DeviceStatusHistory();
                deviceStatusHistory.setDeviceId(-1);
                deviceStatusHistory.setDeviceName(req.getDeviceName()[i]);
                deviceStatusHistory.setDeviceParaName(req.getDevicePara()[i]);
                deviceStatusHistory.setDeviceParaValue(String.valueOf(req.getStatus1()[i]));
                deviceStatusHistory.setCollectDate(dataTool.seconds2Date(req.getSendingTime()));
                deviceStatusHistoryMapper.save(deviceStatusHistory);

                //delete
                deviceStatusMapper.deleteByNameAndPara(req.getDeviceName()[i],req.getDevicePara()[i]);
                //save deviceStatusHistory
                DeviceStatus deviceStatus=new DeviceStatus();
                deviceStatus.setDeviceId(-1);
                deviceStatus.setDeviceName(req.getDeviceName()[i]);
                deviceStatus.setDeviceParaName(req.getDevicePara()[i]);
                deviceStatus.setDeviceParaValue(String.valueOf(req.getStatus1()[i]));
                deviceStatusMapper.save(deviceStatus);
            }

            //


            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 1;//非正常返回
        }
    }

    /**
     * 处理告警信息上报
     * @param reqString 告警信息hex
     * @return 处理结果
     */
    public int handleWarningMessage(String reqString){
        //
        try{
            ByteBuf bb=dataTool.getByteBuf(reqString);
            byte[] reqBytes=dataTool.getBytesFromByteBuf(bb);
            WarningMessage req=new WarningMessage();
            req.decoded(reqBytes);
            System.out.println("save to mysql>>>:");
            // todo save to db and push to mq
            //AlarmHistory
            AlarmHistory alarmHistory=new AlarmHistory();
            alarmHistory.setDeviceId(-1);
            alarmHistory.setAlarmDeviceName(req.getAlarmDeviceName());
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
            alarm.setAlarmTitle(req.getAlarmTitle());
            alarm.setAlarmContent(req.getAlarmContent());
            alarm.setAlarmLevel(req.getAlarmLevel());
            alarm.setAlarmDate(dataTool.seconds2Date(req.getSendingTime()));
            alarmMapper.save(alarm);
            //push message
            String pushMsg=req.getAlarmLevel()+":"+req.getAlarmDeviceName()+","+req.getAlarmTitle()+","+req.getAlarmContent();
            mqService.pushToUser(1,pushMsg);
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 1;//非正常返回
        }
    }
}
