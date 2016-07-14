package com.cmos.ipg.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.cmos.core.bean.InputObject;
import com.cmos.core.bean.OutputObject;
import com.cmos.ipg.bean.StatusMessage;
import com.cmos.ipg.bean.WarningMessage;
import com.cmos.ipg.dubbo.IControlIPGService;
import com.cmos.ipg.entity.Alarm;
import com.cmos.ipg.entity.AlarmHistory;
import com.cmos.ipg.mapper.AgentMapper;
import com.cmos.ipg.utils.DataTool;

import io.netty.buffer.ByteBuf;

/**
 * 向应用平台发送数据接口
 * 
 * @author Administrator
 *
 */
@Service
public class CWPCoreService {
	@Autowired
	DataTool dataTool;
	@Autowired
	IControlIPGService controlService;
	@Autowired
	AgentMapper agentMapper;
	@Autowired
	MQService mqService;

	private Logger _logger = LoggerFactory.getLogger(CWPCoreService.class);

	/**
	 * 处理告警信息
	 * 
	 * @author ybb
	 * @param reqString
	 *            告警信息hex
	 * @return 处理结果
	 */
	@SuppressWarnings("unchecked")
	public int sendWarningMessage(String reqString) {

		try {
			InputObject io = new InputObject();
			io.setMethod("insertDeviceAccessByAgent");
			io.setService("DeviceOpenDetailsService");
			Map map = new HashMap<String, Object>();

			ByteBuf bb = dataTool.getByteBuf(reqString);
			byte[] reqBytes = dataTool.getBytesFromByteBuf(bb);
			WarningMessage req = new WarningMessage();
			req.decoded(reqBytes);
			map.put("deviceName", req.getAlarmDeviceName());
			map.put("deviceCode", req.getAlarmDeviceCode());
			map.put("deviceLoction", req.getAlarmDeviceLocate());
			map.put("paraName", req.getAlarmTitle());
			map.put("paraValue", req.getAlarmContent());
			map.put("sendTime", new Date());
			io.setParams(map);
			OutputObject oo = controlService.execute(io);
			if (oo != null && oo.getReturnCode() != null && oo.getReturnCode().equals("0")) {
			}else{
				return 1;
			}
			System.out.println(oo.getReturnMessage());
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;// 非正常返回
		}
	}

	/**
	 * 处理能耗信息
	 * 
	 * @author ybb
	 * @param reqString
	 *            能耗信息
	 * @return
	 */
	public int sendEnergyRecordMessage(String reqString) {
		try {
			InputObject io = new InputObject();
			io.setMethod("insertEnergyDetailsByAgent");
			io.setService("DeviceOpenDetailsService");
			Map map = new HashMap<String, Object>();

			ByteBuf bb = dataTool.getByteBuf(reqString);
			byte[] reqBytes = dataTool.getBytesFromByteBuf(bb);
			StatusMessage req = new StatusMessage();
			req.decoded(reqBytes);

			map.put("deviceName", req.getDeviceName()[0]);
			map.put("deviceCode", req.getDeviceCode()[0]);
			map.put("deviceLoction", req.getDeviceLocate()[0]);
			map.put("paraName", req.getDevicePara());
			map.put("paraValue", req.getStatus1());
			map.put("sendTime", req.getSendingTime());
			io.setParams(map);
			OutputObject oo = controlService.execute(io);
			if (oo != null && oo.getReturnCode() != null && oo.getReturnCode().equals("0")) {
			} else {
				return 1;
			}
			System.out.println(oo.getReturnMessage());
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;// 非正常返回
		}
	}

	/**
	 * 处能楼控信息
	 * 
	 * @author ybb
	 * @param reqString
	 *            楼控信息
	 * @return
	 */
	public int sendDeviceRecordMessage(String reqString) {
		try {
			InputObject io = new InputObject();
			io.setMethod("insertDeviceOpenDetailsByAgent");
			io.setService("DeviceOpenDetailsService");
			Map map = new HashMap<String, Object>();
			ByteBuf bb = dataTool.getByteBuf(reqString);
			byte[] reqBytes = dataTool.getBytesFromByteBuf(bb);
			StatusMessage req = new StatusMessage();
			req.decoded(reqBytes);
		   List<String> alarmParam = new ArrayList<String>();
		   for(int i=0;i<req.getPackageNum();i++){
			   if(req.getDevicePara()[i].equals("deviceFaultAlarm")){
                   alarmParam.add("运行异常告警#0") ;
           }

		   }
			String[] alarms = new String[alarmParam.size()];
            for(int i=0;i<alarmParam.size();i++){
            	alarms[i]=alarmParam.get(i).toString();
            }
            map.put("alarm", alarms);
			map.put("deviceName", req.getDeviceName()[0]);
			map.put("deviceCode", req.getDeviceCode()[0]);
			map.put("deviceLoction", req.getDeviceLocate()[0]);
			map.put("paraName", req.getDevicePara());
			map.put("paraValue", req.getStatus1());
			map.put("sendTime", req.getSendingTime());
			io.setParams(map);
			OutputObject oo = controlService.execute(io);
			if (oo != null && oo.getReturnCode() != null && oo.getReturnCode().equals("0")) {
			} else {
				return 1;
			}
			System.out.println(oo.getReturnMessage());
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;// 非正常返回
		}
	}

}
