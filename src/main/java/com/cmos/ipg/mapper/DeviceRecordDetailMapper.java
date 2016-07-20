package com.cmos.ipg.mapper;

import com.cmos.ipg.entity.DeviceRecordDetail;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
@Mapper
public interface DeviceRecordDetailMapper {
    @Select("SELECT * FROM ip_device_record_detail WHERE device_id=#{deviceId} and park_code=#{parkCode} LIMIT 1")
    @Results(value ={
            @Result(property="deviceId",column="device_id",javaType=Integer.class,jdbcType= JdbcType.INTEGER),
            @Result(property="deviceName",column="device_name",javaType=String.class,jdbcType=JdbcType.VARCHAR),
            @Result(property="deviceCode",column="device_code",javaType=String.class,jdbcType=JdbcType.VARCHAR),
            @Result(property="deviceLocation",column="device_location",javaType=String.class,jdbcType=JdbcType.VARCHAR),
            @Result(property="deviceParaName",column="device_para_name",javaType=String.class,jdbcType=JdbcType.VARCHAR),
            @Result(property="deviceParaValue",column="device_para_value",javaType=String.class,jdbcType=JdbcType.VARCHAR) })
    DeviceRecordDetail findByDeviceCode(@Param("deviceId") int deviceId ,@Param("parkCode") String parkCode);

    @Insert("INSERT INTO ip_device_record_detail (device_id,device_name,device_code,device_location,device_para_name,device_para_value)" +
            "VALUES(#{deviceRecordDetail.deviceId},#{deviceRecordDetail.deviceName},#{deviceRecordDetail.deviceCode},#{deviceRecordDetail.deviceLocation}," +
            "#{deviceRecordDetail.deviceParaName},#{deviceRecordDetail.deviceParaValue})")
    void save(@Param("deviceRecordDetail")DeviceRecordDetail deviceRecordDetail);

    @Delete("DELETE  FROM ip_device_record_detail WHERE device_name = #{deviceName}and device_id = #{deviceId} and park_code=#{parkCode}")
    void deleteByNameAndCode(@Param("deviceName")String deviceNamem,@Param("deviceId")int deviceId,@Param("parkCode") String parkCode);

    @Update("UPDATE ip_device_record_detail SET device_name = #{deviceName} WHERE device_id = #{deviceId} and park_code=#{parkCode}")
    void update(@Param("deviceName") String deviceName,@Param("deviceId") int deviceId,@Param("parkCode") String parkCode);
}
