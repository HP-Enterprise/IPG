package com.cmos.ipg.mapper;


import com.cmos.ipg.entity.DeviceRecord;

import java.util.Date;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.type.JdbcType;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
@Mapper
public interface DeviceRecordMapper {

    @Select("SELECT * FROM ip_device_record WHERE device_code=#{deviceCode}LIMIT 1")
    @Results(value ={
            @Result(property="deviceName",column="device_name",javaType=String.class,jdbcType= JdbcType.VARCHAR),
            @Result(property="deviceSy",column="device_sy",javaType=Date.class,jdbcType=JdbcType.TIMESTAMP),
            @Result(property="deviceType",column="device_type",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
            @Result(property="deviceLocate",column="device_locate",javaType=String.class,jdbcType=JdbcType.VARCHAR),
            @Result(property="deviceCode",column="device_code",javaType=String.class,jdbcType=JdbcType.VARCHAR),
            @Result(property="parkCode",column="park_code",javaType=String.class,jdbcType=JdbcType.VARCHAR) })
    DeviceRecord findByDeviceCode(@Param("deviceCode") String deviceCode);

    @Insert("INSERT INTO ip_device_record (device_name,device_sy,device_type,device_locate,device_code,park_code)" +
            "VALUES(#{deviceRecord.deviceName},#{deviceRecord.deviceSy},#{deviceRecord.deviceType},#{deviceRecord.deviceLocate},#{deviceRecord.deviceCode},#{deviceRecord.parkCode})")
    @SelectKey(before=false,keyProperty="deviceRecord.id",resultType=Integer.class,statementType=StatementType.STATEMENT,statement="SELECT LAST_INSERT_ID() AS id")
    void save(@Param("deviceRecord")DeviceRecord deviceRecord);

    @Delete("DELETE  FROM ip_device_record WHERE device_name = #{deviceName}and deviceCode = #{deviceCode}")
    void deleteByNameAndCode(@Param("deviceName")String deviceNamem,@Param("deviceCode")int deviceCode);

    @Update("UPDATE ip_device_record SET device_name = #{deviceName} WHERE device_code = #{deviceCode}")
    void update(@Param("deviceName") String deviceName,@Param("deviceCode") int deviceCode);
}
