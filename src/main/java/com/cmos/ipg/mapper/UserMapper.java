package com.cmos.ipg.mapper;
import com.cmos.ipg.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by jackl on 2016/4/28.
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM t_user WHERE username = #{username}")
    User findByName(@Param("username") String username);

    List<User> getUsers();

    User getUser(long id);


    @Insert("INSERT INTO t_user(username, password)" +
            "VALUES(#{user.username}, #{user.password})")
    void addUser(@Param("user")User user);

    void clear();

    void updateUser(User user);

}