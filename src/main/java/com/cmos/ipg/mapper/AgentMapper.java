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

    @Select("SELECT * FROM ip_agent WHERE ip = #{ip} limit 1")
    @Results(value = {
            @Result(property = "agentName", column = "agent_name", javaType = String.class, jdbcType = JdbcType.VARCHAR) ,
            @Result(property = "agentType", column = "agent_type", javaType = Short.class, jdbcType = JdbcType.SMALLINT) ,
            @Result(property = "conProtocol", column = "con_protocol", javaType = Integer.class, jdbcType = JdbcType.INTEGER) })
    Agent findByAgentIp(@Param("ip") String  ip);

    @Insert("INSERT INTO ip_agent(agent_name,agent_type,num, ip,port,contable,con_protocol,description)" +
            "VALUES(#{agent.agentName},#{agent.agentType},#{agent.num}, #{agent.ip}, #{agent.port}, #{agent.contable}, #{agent.conProtocol}, #{agent.description})")
    void save(@Param("agent") Agent agent);
}
