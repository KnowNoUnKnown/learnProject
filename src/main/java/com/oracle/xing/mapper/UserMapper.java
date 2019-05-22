package com.oracle.xing.mapper;

import com.oracle.xing.model.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * Created by Administrator on 2018/10/15.
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where id = ${id}")
    User selectById(String id);

    @Select("select * from user")
    List<User> query(User user);
}
