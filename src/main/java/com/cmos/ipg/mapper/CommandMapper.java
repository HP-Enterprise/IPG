package com.cmos.ipg.mapper;

import com.cmos.ipg.entity.Agent;
import com.cmos.ipg.entity.Command;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;

/**
 * Created by jackl on 2016/5/5.
 */
@Mapper
public interface CommandMapper {

    @Select("SELECT * FROM ip_command where command_status=0 order by  action_date desc limit 1")
    @Results(value = {
            @Result(property = "commandType", column = "command_type", javaType = Short.class, jdbcType = JdbcType.SMALLINT) ,
            @Result(property = "commandStatus", column = "command_status", javaType = Short.class, jdbcType = JdbcType.SMALLINT) ,
            @Result(property = "actionDate", column = "action_date", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP) })
    Command findOne();

    @Insert("INSERT INTO ip_command(command_type,num,action, param,command_status,action_date)" +
            "VALUES(#{command.commandType},#{command.num},#{command.action}, #{command.param}, #{command.commandStatus}, #{command.actionDate})")
    void save(@Param("command") Command command);

    @Update("update ip_command  set command_type=#{command.commandType},num=#{command.num},action=#{command.action}, param=#{command.param},command_status=#{command.commandStatus},action_date=#{command.actionDate} where id=#{command.id}")
    void update(@Param("command") Command command);
}
