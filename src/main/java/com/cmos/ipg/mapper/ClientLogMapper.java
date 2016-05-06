package com.cmos.ipg.mapper;
import com.cmos.ipg.entity.ClientLog;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

/**
 * Created by jackl on 2016/4/28.
 */
@Mapper
public interface ClientLogMapper {



    @Insert("INSERT INTO ip_client_log(client, action,action_date)" +
            "VALUES(#{clientLog.client}, #{clientLog.action},#{clientLog.actionDate})")
    void save(@Param("clientLog") ClientLog clientLog);


    @Select("SELECT * FROM ip_client_log order by id desc")
    @Results(value = {
            @Result(property = "actionDate", column = "action_date", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP) })
    List<ClientLog> findAll();

}