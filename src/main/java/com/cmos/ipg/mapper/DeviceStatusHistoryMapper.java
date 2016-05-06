package com.cmos.ipg.mapper;

import com.cmos.ipg.entity.DeviceStatus;
import com.cmos.ipg.entity.DeviceStatusHistory;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;

/**
 * Created by jackl on 2016/5/5.
 */
@Mapper
public interface DeviceStatusHistoryMapper {

    @Select("SELECT * FROM ip_device_status_his WHERE id = #{id}")
    @Results(value = {
            @Result(property = "deviceId", column = "device_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "deviceParaName", column = "device_para_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "deviceParaValue", column = "device_para_value", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "collectDate", column = "collect_date", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP) })
    DeviceStatusHistory findById(@Param("id") int id);

    @Insert("INSERT INTO ip_device_status_his(device_id, device_para_name,device_para_value,collect_date)" +
            "VALUES(#{deviceStatusHistory.deviceId}, #{deviceStatusHistory.deviceParaName}, #{deviceStatusHistory.deviceParaValue}, #{deviceStatusHistory.collectDate})")
    void save(@Param("deviceStatusHistory") DeviceStatusHistory deviceStatusHistory);
}