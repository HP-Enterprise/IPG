package com.cmos.ipg.mapper;
import com.cmos.ipg.entity.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by jackl on 2016/4/28.
 */
@Mapper
public interface DataMapper {

    @Select("SELECT * FROM ip_data WHERE from = #{from}")
    Data findByFrom(@Param("from") String from);

    @Insert("INSERT INTO ip_data(client, bytes,create_date)" +
            "VALUES(#{data.client}, #{data.bytes}, #{data.createDate})")
    void save(@Param("data")Data data);



}