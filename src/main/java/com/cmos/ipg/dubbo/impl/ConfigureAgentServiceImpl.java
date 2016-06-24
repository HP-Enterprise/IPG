package com.cmos.ipg.dubbo.impl;

import com.cmos.ipg.dubbo.ConfigureAgentService;
import com.cmos.ipg.entity.Agent;
import com.cmos.ipg.mapper.AgentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <code>配置Agent客户端信息实现类</code>
 * @author Hardy
 * @date 2016/6/22
 */
@Component
public class ConfigureAgentServiceImpl implements ConfigureAgentService {
    @Autowired
    AgentMapper agentMapper;

    @Override
    public void addAgentInfo(Agent agent) {
        agentMapper.save(agent);
    }

    @Override
    public void updateAgentInfo(Agent agent) {

    }

    @Override
    public boolean deleteAgentInfo(String ip) {
        agentMapper.delete(ip);
        return true;
    }

    @Override
    public List<Agent> selectAllAgentInfo() {
        return agentMapper.getAllAgent();
    }
}
