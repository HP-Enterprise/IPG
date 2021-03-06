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

    @Select("SELECT * FROM ip_data WHERE from = #{from} and park_code=#{parkCode}")
    Data findByFrom(@Param("from") String from,@Param("parkCode") String parkCode);

    @Insert("INSERT INTO ip_data(client, bytes,action_date,park_code)" +
            "VALUES(#{data.client}, #{data.bytes}, #{data.actionDate},#{data.parkCode})")
    void save(@Param("data")Data data);



}