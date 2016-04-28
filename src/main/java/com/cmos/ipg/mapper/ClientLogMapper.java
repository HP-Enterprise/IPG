package com.cmos.ipg.mapper;
import com.cmos.ipg.entity.ClientLog;
import com.cmos.ipg.entity.Data;
import com.cmos.ipg.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by jackl on 2016/4/28.
 */
@Mapper
public interface ClientLogMapper {



    @Insert("INSERT INTO t_client_log(client, action)" +
            "VALUES(#{clientLog.client}, #{clientLog.action})")
    void save(@Param("clientLog") ClientLog clientLog);



}