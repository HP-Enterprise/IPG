package com.cmos.ipg.mapper;

import com.cmos.ipg.entity.Agent;
import com.cmos.ipg.entity.Device;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * Created by jackl on 2016/5/5.
 */
@Mapper
public interface AgentMapper {

    @Select("SELECT * FROM ip_agent WHERE ip = #{ip} and port = #{port}  limit 1")
    @Results(value = {
            @Result(property = "agentName", column = "agent_name", javaType = String.class, jdbcType = JdbcType.VARCHAR) ,
            @Result(property = "agentType", column = "agent_type", javaType = Short.class, jdbcType = JdbcType.SMALLINT) ,
            @Result(property = "conProtocol", column = "con_protocol", javaType = String.class, jdbcType = JdbcType.INTEGER),
            @Result(property="parkCode",column="park_code",javaType=String.class,jdbcType=JdbcType.VARCHAR)})
    Agent findByAgentIp(@Param("ip") String  ip,@Param("port") int port);

    @Insert("INSERT INTO ip_agent(id,agent_name,agent_type,num, ip,port,contable,con_protocol,description,agent_online,park_code)" +
            "VALUES(#{agent.id},#{agent.agentName},#{agent.agentType},#{agent.num}, #{agent.ip}, #{agent.port}, #{agent.contable}, #{agent.conProtocol}, #{agent.description},#{agent.agentOnline},#{agent.parkCode})")
//    @SelectKey(before=false,keyProperty="agent.id",resultType=Integer.class,statementType=StatementType.STATEMENT,statement="SELECT LAST_INSERT_ID() AS id")
    void save(@Param("agent") Agent agent);

    @Update("UPDATE ip_agent SET agent_online = #{agentOnline} WHERE ip = #{ip} and port = #{port}")
    void update(@Param("agentOnline") short agentOnline,@Param("ip") String ip,@Param("port") int port);

    @Select("SELECT * FROM ip_agent WHERE agent_type = #{agent_type} and num=#{num} limit 1")
    @Results(value = {
            @Result(property = "agentName", column = "agent_name", javaType = String.class, jdbcType = JdbcType.VARCHAR) ,
            @Result(property = "agentType", column = "agent_type", javaType = Short.class, jdbcType = JdbcType.SMALLINT) ,
            @Result(property = "conProtocol", column = "con_protocol", javaType = String.class, jdbcType = JdbcType.INTEGER),
            @Result(property="parkCode",column="park_code",javaType=String.class,jdbcType=JdbcType.VARCHAR)})
    Agent findByAgentTypeAndNum(@Param("agent_type") short  agent_type,@Param("num") int  num);

    @Delete("DELETE from ip_agent where id = #{id} ")
    void delete(@Param("id") String id);

    @Select("SELECT * FROM ip_agent WHERE 1=1 ")
    List<Agent> getAllAgent();
    
    @Select("SELECT * FROM ip_agent WHERE  num=#{num}  limit 1")
    @Results(value = {
    		@Result(property = "agentName", column = "agent_name", javaType = String.class, jdbcType = JdbcType.VARCHAR) ,
    		@Result(property = "agentType", column = "agent_type", javaType = Short.class, jdbcType = JdbcType.SMALLINT) ,
    		@Result(property = "conProtocol", column = "con_protocol", javaType = String.class, jdbcType = JdbcType.INTEGER),
    		@Result(property="parkCode",column="park_code",javaType=String.class,jdbcType=JdbcType.VARCHAR)})
    Agent findByAgentNum(@Param("num") int  num);
    @Update("UPDATE ip_agent SET agent_name = #{agent.agentName} ,"
    		+ "agent_type = #{agent.agentType} ,"
    		+ "num = #{agent.num},"
    		+ "ip = #{agent.ip},"
    		+ "port = #{agent.port},"
    		+ "contable = #{agent.contable},"
    		+ "con_protocol = #{agent.conProtocol} ,"
    		+ "description = #{agent.description},"
//    		+ "agent_online = #{agent.agent_online} ,"
    		+ "park_code = #{agent.parkCode}  WHERE id = #{agent.id}")
    void updateAllParam(@Param("agent") Agent agent);

}
