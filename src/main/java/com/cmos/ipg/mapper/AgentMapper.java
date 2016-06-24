package com.cmos.ipg.mapper;

import com.cmos.ipg.entity.Agent;
import com.cmos.ipg.entity.Device;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

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

    @Insert("INSERT INTO ip_agent(agent_name,agent_type,num, ip,port,contable,con_protocol,description,agent_online)" +
            "VALUES(#{agent.agentName},#{agent.agentType},#{agent.num}, #{agent.ip}, #{agent.port}, #{agent.contable}, #{agent.conProtocol}, #{agent.description},#{agent.agentOnline})")
    void save(@Param("agent") Agent agent);

    @Update("UPDATE ip_agent SET agent_online = #{agentOnline} WHERE ip = #{ip}")
    void update(@Param("agentOnline") short agentOnline,@Param("ip") String ip);

    @Select("SELECT * FROM ip_agent WHERE agent_type = #{agent_type} and num=#{num}  limit 1")
    @Results(value = {
            @Result(property = "agentName", column = "agent_name", javaType = String.class, jdbcType = JdbcType.VARCHAR) ,
            @Result(property = "agentType", column = "agent_type", javaType = Short.class, jdbcType = JdbcType.SMALLINT) ,
            @Result(property = "conProtocol", column = "con_protocol", javaType = Integer.class, jdbcType = JdbcType.INTEGER) })
    Agent findByAgentTypeAndNum(@Param("agent_type") short  agent_type,@Param("num") int  num);

    @Delete("Delete from ip_agent where id = #{id}")
    void delete(@Param("id") String id);

    @Select("SELECT * FROM ip_agent WHERE 1=1")
    List<Agent> getAllAgent();
}
