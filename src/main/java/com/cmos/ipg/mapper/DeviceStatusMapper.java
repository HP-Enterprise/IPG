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

    @Select("SELECT * FROM ip_device_status WHERE id = #{id}")
    @Results(value = {
            @Result(property = "deviceId", column = "device_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "deviceParaName", column = "device_para_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "deviceParaValue", column = "device_para_value", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
    DeviceStatus findById(@Param("id") int id);

    @Insert("INSERT INTO ip_device_status(device_id, device_para_name,device_para_value)" +
            "VALUES(#{deviceStatus.deviceId}, #{deviceStatus.deviceParaName}, #{deviceStatus.deviceParaValue})")
    void save(@Param("deviceStatus")DeviceStatus deviceStatus);
}
