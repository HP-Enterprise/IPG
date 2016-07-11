package com.cmos.ipg.mapper;

import com.cmos.ipg.entity.EnergyRecordDetail;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
@Mapper
public interface EnergyRecordDetailMapper {
    @Select("SELECT * FROM ip_energy_record_detail WHERE device_id=#{deviceId}LIMIT 1")
    @Results(value = {
            @Result(property = "deviceId", column = "device_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "deviceName", column = "device_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "deviceCode", column = "device_code", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "deviceLocation", column = "device_location", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "deviceParaName", column = "device_para_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "deviceParaValue", column = "device_para_value", javaType = String.class, jdbcType = JdbcType.VARCHAR)})
    EnergyRecordDetail findByDeviceCode(@Param("deviceId") int deviceId);

    @Insert("INSERT INTO ip_energy_record_detail (device_id,device_name,device_code,device_location,device_para_name,device_para_value)" +
            "VALUES(#{energyRecordDetail.deviceId},#{energyRecordDetail.deviceName},#{energyRecordDetail.deviceCode},#{energyRecordDetail.deviceLocation}," +
            "#{energyRecordDetail.deviceParaName},#{energyRecordDetail.deviceParaValue})")
    void save(@Param("energyRecordDetail") EnergyRecordDetail energyRecordDetail);

    @Delete("DELETE  FROM ip_energy_record_detail WHERE device_name = #{deviceName}and device_id = #{deviceId}")
    void deleteByNameAndCode(@Param("deviceName") String deviceNamem, @Param("deviceId") int deviceId);

    @Update("UPDATE ip_energy_record_detail SET device_name = #{deviceName} WHERE device_id = #{deviceId}")
    void update(@Param("deviceName") String deviceName, @Param("deviceId") int deviceId);
}