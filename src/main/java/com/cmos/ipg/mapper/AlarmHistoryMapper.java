package com.cmos.ipg.mapper;

import com.cmos.ipg.entity.Alarm;
import com.cmos.ipg.entity.AlarmHistory;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;

/**
 * Created by jackl on 2016/5/5.
 */
@Mapper
public interface AlarmHistoryMapper {

    @Select("SELECT * FROM ip_alarm_his WHERE device_id = #{deviceId} LIMIT 1")
    @Results(value = {
            @Result(property = "deviceId", column = "device_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "alarmDeviceName", column = "alarm_device_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "alarmTitle", column = "alarm_title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "alarmContent", column = "alarm_content", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "alarmLevel", column = "alarm_level", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "alarmDate", column = "alarm_date", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP) })
    AlarmHistory findByDeviceId(@Param("deviceId") int deviceId);

    @Insert("INSERT INTO ip_alarm_his(device_id, alarm_device_name,alarm_title,alarm_content,alarm_level,alarm_date)" +
            "VALUES(#{alarmHistory.deviceId}, #{alarmHistory.alarmDeviceName}, #{alarmHistory.alarmTitle}, #{alarmHistory.alarmContent}, #{alarmHistory.alarmLevel}, #{alarmHistory.alarmDate})")
    void save(@Param("alarmHistory") AlarmHistory alarmHistory);
}
