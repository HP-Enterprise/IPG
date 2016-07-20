package com.cmos.ipg.mapper;

import com.cmos.ipg.entity.Device;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;

/**
 * Created by jackl on 2016/5/5.
 */
@Mapper
public interface DeviceMapper {

    @Select("SELECT * FROM ip_device WHERE device_sn = #{deviceSn} and park_code=#{parkCode} limit 1")
    @Results(value = {
            @Result(property = "deviceName", column = "device_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "deviceSn", column = "device_sn", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "deviceType", column = "device_type", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "deviceLocate", column = "device_locate", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property="parkCode",column="park_code",javaType=String.class,jdbcType=JdbcType.VARCHAR)})
    Device findBySn(@Param("deviceSn") String deviceSn,@Param("parkCode") String parkCode);

    @Insert("INSERT INTO ip_device(device_name, device_sn,device_type,device_locate,park_code)" +
            "VALUES(#{device.deviceName}, #{device.deviceSn}, #{device.deviceType}, #{device.deviceLocate},#{device.parkCode})")
    void save(@Param("device")Device device);
}
