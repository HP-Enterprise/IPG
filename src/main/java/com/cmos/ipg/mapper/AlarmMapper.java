package com.cmos.ipg.mapper;

import com.cmos.ipg.entity.Alarm;
import com.cmos.ipg.entity.DeviceStatusHistory;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;

/**
 * Created by jackl on 2016/5/5.
 */
@Mapper
public interface AlarmMapper {

    @Select("SELECT * FROM ip_alarm WHERE device_id = #{deviceId} LIMIT 1")
    @Results(value = {
            @Result(property = "deviceId", column = "device_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "alarmDeviceName", column = "alarm_device_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "alarmDeviceCode", column = "alarm_device_code", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "alarmDeviceLocate", column = "alarm_device_locate", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "alarmTitle", column = "alarm_title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "alarmContent", column = "alarm_content", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "alarmLevel", column = "alarm_level", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "alarmDate", column = "alarm_date", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP) })
    Alarm findByDeviceId(@Param("deviceId") int deviceId);

    @Insert("INSERT INTO ip_alarm(device_id, alarm_device_name, alarm_device_code, alarm_device_locate,alarm_title,alarm_content,alarm_level,alarm_date)" +
            "VALUES(#{alarm.deviceId}, #{alarm.alarmDeviceName}, #{alarm.alarmDeviceCode}, #{alarm.alarmDeviceLocate}, #{alarm.alarmTitle}, #{alarm.alarmContent}, #{alarm.alarmLevel}, #{alarm.alarmDate})")
    void save(@Param("alarm") Alarm alarm);


    @Delete("DELETE  FROM ip_alarm WHERE alarm_device_name = #{alarmDeviceName}")
    void deleteByName(@Param("alarmDeviceName") String alarmDeviceName);
}
