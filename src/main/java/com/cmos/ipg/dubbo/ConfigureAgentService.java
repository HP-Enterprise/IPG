package com.cmos.ipg.dubbo;

import com.cmos.ipg.entity.Agent;

import java.util.List;

/**
 * <code>配置Agent客户端信息</code>
 * @author Hardy
 * @date 2016/6/22
 */
public interface ConfigureAgentService {

    /**
     * <code>新增Agent配置信息</code>
     * @param agent
     * @return
     */
    void addAgentInfo(Agent agent);

    /**
     * <code>修改Agent配置信息</code>
     * @param agent
     * @return
     */
    void updateAgentInfo(Agent agent);

    /**
     * <code>删除Agent配置信息</code>
     * @param ip
     * @return
     */
    boolean deleteAgentInfo(String ip);

    /**
     * <code>获取所有的Agent信息</code>
     * @return
     */
    List<Agent> selectAllAgentInfo();

}
