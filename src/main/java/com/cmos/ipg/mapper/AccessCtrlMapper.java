package com.cmos.ipg.mapper;


import com.cmos.ipg.entity.AccessCtrl;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;


/**
 * 门禁控制的mapper接口
 * @author ybb
 * Created by Administrator on 2016/7/18 0018.
 */

@Mapper
public interface AccessCtrlMapper {
    @Select("SELECT * FROM ip_access_ctrl WHERE id = #{Id} and park_code=#{parkCode} LIMIT 1")
    @Results(value = {
            @Result(property = "id", column = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "aAlias", column = "a_alias", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "aArea", column = "a_area", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "aNum", column = "a_num", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "ip", column = "ip", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "interfaceAddr", column = "interface_addr", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "interfacePosition", column = "interface_position", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "aType", column = "a_type", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "taskCode", column = "task_code", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "eventCode", column = "event_code", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "msgTypeId", column = "msg_type_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "description", column = "description", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "ioMsgCode", column = "io_msg_code", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "eventTime", column = "event_time", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "controllerName", column = "controller_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "panelName", column = "panel_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "cardReaderName", column = "card_reader_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "eventName", column = "event_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "ioDescription", column = "io_description", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "cardNum", column = "card_num", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "jobNum", column = "job_num", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "staffName", column = "staff_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "parkCode", column = "park_code", javaType = String.class, jdbcType = JdbcType.VARCHAR)})
    AccessCtrl findByAccessCtrl(@Param("id") int id ,@Param("parkCode") String parkCode);

    @Insert("INSERT INTO ip_access_ctrl(id,a_alias,a_area,a_num,ip,interface_addr,interface_position,a_type,task_code,event_code," +
            "msg_type_id,description,io_msg_code,event_time,controller_name,panel_name,card_reader_name,event_name,io_description,card_num,job_num,staff_name,park_code)" +
            "VALUES(#{accessCtrl.id},#{accessCtrl.aAlias},#{accessCtrl.aArea},#{accessCtrl.aNum},#{accessCtrl.ip},#{accessCtrl.interfaceAddr}," +
            "#{accessCtrl.interfacePosition},#{accessCtrl.aType},#{accessCtrl.taskCode},#{accessCtrl.eventCode},#{accessCtrl.msgTypeId},#{accessCtrl.description},#{accessCtrl.ioMsgCode}," +
            "#{accessCtrl.eventTime},#{accessCtrl.controllerName},#{accessCtrl.panelName},#{accessCtrl.cardReaderName},#{accessCtrl.eventName},#{accessCtrl.ioDescription},#{accessCtrl.cardNum}," +
            "#{accessCtrl.jobNum},#{accessCtrl.staffName},#{accessCtrl.parkCode})")
    void save(@Param("accessCtrl") AccessCtrl accessCtrl);
}
