package com.cmos.ipg.mapper;

import com.cmos.ipg.entity.Alarm;
import com.cmos.ipg.entity.AlarmConf;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;

/**
 * Created by jackl on 2016/5/5.
 */
@Mapper
public interface AlarmConfMapper {

    @Select("SELECT * FROM ip_alarm_conf WHERE device_id = #{deviceId} LIMIT 1")
    @Results(value = {
            @Result(property = "deviceId", column = "device_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "deviceName", column = "device_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "deviceParaName", column = "device_para_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "deviceParaValue", column = "device_para_value", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "alarmTitle", column = "alarm_title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "alarmContent", column = "alarm_content", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "alarmLevel", column = "alarm_level", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "alarmDate", column = "alarm_date", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP) })
    AlarmConf findByDeviceId(@Param("deviceId") int deviceId);

    @Insert("INSERT INTO ip_alarm_conf(device_id, device_name, device_para_name,device_para_value,alarm_title,alarm_content,alarm_level,alarm_date)" +
            "VALUES(#{alarmConf.deviceId}, #{alarmConf.deviceName},  #{alarmConf.deviceParaName}, #{alarmConf.deviceParaValue},#{alarmConf.alarmTitle}, #{alarmConf.alarmContent}, #{alarmConf.alarmLevel}, #{alarmConf.alarmDate})")
    void save(@Param("alarmConf") AlarmConf alarmConf);
}
