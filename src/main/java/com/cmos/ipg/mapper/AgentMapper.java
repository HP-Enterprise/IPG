package com.cmos.ipg.mapper;

import com.cmos.ipg.entity.Agent;
import com.cmos.ipg.entity.Device;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

/**
 * Created by jackl on 2016/5/5.
 */
@Mapper
public interface AgentMapper {

    @Select("SELECT * FROM ip_agent WHERE agent_name = #{agentName} limit 1")
    @Results(value = {
            @Result(property = "agentName", column = "agent_name", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
    Agent findByAgentName(@Param("agentName") String  agentName);

    @Insert("INSERT INTO ip_agent(agent_name, ip,port,contable)" +
            "VALUES(#{agent.agentName}, #{agent.ip}, #{agent.port}, #{agent.contable})")
    void save(@Param("agent") Agent agent);
}
