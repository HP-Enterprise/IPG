package com.cmos.ipg.mapper;

import com.cmos.ipg.entity.Data;
import com.cmos.ipg.entity.Device;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by jackl on 2016/5/5.
 */
@Mapper
public interface DeviceMapper {

    @Select("SELECT * FROM ip_device WHERE id = #{id}")
    Device findById(@Param("id") int id);

    @Insert("INSERT INTO ip_device(device_name, device_sn,device_type,device_locate)" +
            "VALUES(#{device.deviceName}, #{device.deviceSn}, #{device.deviceType}, #{device.deviceLocate})")
    void save(@Param("device")Device device);
}
