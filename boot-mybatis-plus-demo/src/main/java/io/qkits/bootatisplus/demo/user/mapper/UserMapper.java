package io.qkits.bootatisplus.demo.user.mapper;


import io.qkits.bootatisplus.demo.base.mapper.MyBaseMapper;
import io.qkits.bootatisplus.demo.base.page.UserPage;
import io.qkits.bootatisplus.demo.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends MyBaseMapper<User> {
    UserPage selectUserPage(UserPage userPage);

    List<User> findList(@Param("user") User user);
}
