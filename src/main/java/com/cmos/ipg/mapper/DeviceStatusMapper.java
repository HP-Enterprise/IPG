package com.cmos.ipg.mapper;

import com.cmos.ipg.entity.Device;
import com.cmos.ipg.entity.DeviceStatus;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

/**
 * Created by jackl on 2016/5/5.
 */
@Mapper
public interface DeviceStatusMapper {

    @Select("SELECT * FROM ip_device_status WHERE device_id = #{deviceId} LIMIT 1")
    @Results(value = {
            @Result(property = "deviceId", column = "device_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "deviceName", column = "device_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "deviceCode", column = "device_code", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "deviceLocation", column = "device_location", javaType = String.class, jdbcType = JdbcType.VARCHAR),
           @Result(property = "deviceParaName", column = "device_para_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "deviceParaValue", column = "device_para_value", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
    DeviceStatus findByDeviceId(@Param("deviceId") int deviceId);

    @Insert("INSERT INTO ip_device_status(device_id, device_name,device_code,device_location,device_para_name,device_para_value)" +
            "VALUES(#{deviceStatus.deviceId}, #{deviceStatus.deviceName},#{deviceStatus.deviceCode},#{deviceStatus.deviceLocation},#{deviceStatus.deviceParaName}, #{deviceStatus.deviceParaValue})")
    void save(@Param("deviceStatus")DeviceStatus deviceStatus);

    @Delete("DELETE  FROM ip_device_status WHERE device_name = #{deviceName}and  device_para_name = #{deviceParaName}")
    void deleteByNameAndPara(@Param("deviceName") String deviceName,@Param("deviceParaName") String deviceParaName);
}
