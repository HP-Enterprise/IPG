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

    @Select("SELECT * FROM ip_device_status_his WHERE device_id = #{deviceId} limit 1")
    @Results(value = {
            @Result(property = "deviceId", column = "device_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "deviceName", column = "device_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "deviceCode", column = "device_code", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "deviceLocation", column = "device_location", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "deviceParaName", column = "device_para_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "deviceParaValue", column = "device_para_value", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "collectDate", column = "collect_date", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP) })
    DeviceStatusHistory findByDeviceId(@Param("deviceId") int deviceId);

    @Insert("INSERT INTO ip_device_status_his(device_id, device_name,decvice_code,device_location,device_para_name,device_para_value,collect_date)" +
            "VALUES(#{deviceStatusHistory.deviceId}, #{deviceStatusHistory.deviceName},#{deviceStatusHistory.deviceCode},#{deviceStatusHistory.deviceLocation}, #{deviceStatusHistory.deviceParaName}, #{deviceStatusHistory.deviceParaValue}, #{deviceStatusHistory.collectDate})")
    void save(@Param("deviceStatusHistory") DeviceStatusHistory deviceStatusHistory);
}
