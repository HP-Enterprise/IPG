package com.cmos.ipg.mapper;
import com.cmos.ipg.entity.ClientLog;
import com.cmos.ipg.entity.Data;
import com.cmos.ipg.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

/**
 * Created by jackl on 2016/4/28.
 */
@Mapper
public interface ClientLogMapper {



    @Insert("INSERT INTO t_client_log(client, action,create_date)" +
            "VALUES(#{clientLog.client}, #{clientLog.action},#{clientLog.createDate})")
    void save(@Param("clientLog") ClientLog clientLog);


    @Select("SELECT * FROM t_client_log order by id desc")
    @Results(value = {
            @Result(property = "createDate", column = "create_date", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP) })
    List<ClientLog> findAll();

}