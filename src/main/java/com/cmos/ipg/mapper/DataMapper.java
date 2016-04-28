package com.cmos.ipg.mapper;
import com.cmos.ipg.entity.Data;
import com.cmos.ipg.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by jackl on 2016/4/28.
 */
@Mapper
public interface DataMapper {

    @Select("SELECT * FROM t_data WHERE from = #{from}")
    User findByFrom(@Param("from") String from);

    @Insert("INSERT INTO t_data(client, bytes)" +
            "VALUES(#{data.client}, #{data.bytes})")
    void save(@Param("data")Data data);



}