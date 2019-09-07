package io.qkits.bootatisplus.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.qkits.bootatisplus.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
