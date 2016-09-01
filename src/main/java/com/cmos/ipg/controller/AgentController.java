package com.cmos.ipg.controller;

import com.cmos.ipg.entity.Agent;
import com.cmos.ipg.mapper.AgentMapper;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Administrator on 2016/7/29 0029.
 */
@RestController
@RequestMapping("/agent")
public class AgentController {
    @Autowired
    AgentMapper agentMapper;

    /**
     * 添加Agent信息的方法
     * @param agent
     */
    @RequestMapping(value = "/agentAdd")
    @ResponseBody
    public String agentAdd(@RequestBody Agent agent){
    	String result="{\"status\":1,\"data\":\"操作失败\"}";
    	try {
    		agentMapper.save(agent);
    		return "{\"status\":0,\"data\":"+agent.getId()+"}";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

    }

    /**
     * 修改agent信息的方法
     * @param agent
     */
    @RequestMapping(value = "/agentUpdate")
    @ResponseBody
    public  String agentUpdate(@RequestBody Agent agent){
    	String result="{\"status\":0,\"data\":\"操作成功\"}";
    	try {
    		agentMapper.updateAllParam(agent);
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"status\":1,\"data\":\"操作失败\"}";
		}
		return result;
    }

    /**
     * 删除agent信息的方法
     * @param id  id
     */
    @RequestMapping(value = "/agentDelete",method = RequestMethod.GET)
    @ResponseBody
    public String  agentDelete(String id){
    	String result="{\"status\":0,\"data\":\"操作成功\"}";
    	try {
    		agentMapper.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"status\":1,\"data\":\"操作失败\"}";
		}
		return result;

    }

    /**
     * 查询agent信息的方法
     * @return
     */
//    @RequestMapping(value = "/get",method = RequestMethod.GET)
//    public String agentGet(){
//        List <Agent>list = agentMapper.getAllAgent();
//        com.alibaba.fastjson.JSONArray jsonArray = new com.alibaba.fastjson.JSONArray(); 
//        jsonArray.add(list);
//        return jsonArray.toString();
//    }

}
