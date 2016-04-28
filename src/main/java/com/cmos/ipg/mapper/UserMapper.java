package com.cmos.ipg.mapper;
import com.cmos.ipg.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by jackl on 2016/4/28.
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM t_User WHERE username = #{username}")
    User findByName(@Param("username") String username);

}