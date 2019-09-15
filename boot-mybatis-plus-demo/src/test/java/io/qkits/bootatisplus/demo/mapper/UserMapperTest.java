package io.qkits.bootatisplus.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import io.qkits.bootatisplus.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testGetOfBaseMapper(){
      log.info("start getting user information");
        List<User> users = userMapper.selectList(null);
        Assert.assertEquals(users.size(),5);
        users.forEach(user -> log.info(user.toString()));
    }

    @Test
    public void testSaveOfBaseMapper(){
        log.info("start save user......");
        User user = new User();
        user.setAge(12);
        user.setEmail("test@email.com");
        user.setId(10L);
        user.setName("name");
        int count = userMapper.insert(user);
        assertThat(count,is(1));
    }

    @Test
    public void useQueryOfBaseMapper(){
        log.info("query user.....");
        Map<String,Object> query = new HashMap<>();
        query.put("age",18);
        List<User> users = userMapper.selectByMap(query);
        System.out.println(users);
    }

    @Test
    public void hardDeleteOfBaseMapper(){
        log.info("hard delete .....");
        int result = userMapper.deleteById(1L);
        assertThat(result,is(1));
    }

    @Test
    public void updateOfBaseMapper(){
        log.info("start update ......");
        User user = new User().setId(1L).setEmail("test@test.com").setAge(12);
        int result = userMapper.updateById(user);
        assertThat(result,is(1));
    }

    @Test
    public void updateWithQueryWrapper(){

    }

}