package io.qkits.benchmarks.db.mybatisplus;

import io.qkits.benchmarks.db.core.DaoBenchmarkService;
import io.qkits.benchmarks.db.entity.User;
import io.qkits.benchmarks.db.mybatisplus.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * QMETA
 * created at: 2020/4/11
 * created by: patrick
 **/
@Service
public class MybatisplusDaoBenchmarkService implements DaoBenchmarkService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void testAdd() {
        userMapper.insert(next());
    }

    @Override
    public void testUnique() {
        userMapper.selectById(1);
    }

    @Override
    public void testUpdateById() {
        User user = new User();
        user.setCode("test");
        user.setId(2);
        userMapper.updateById(user);
    }

    @Override
    public void testPageQuery() {
        Map<String,Object> query = new HashMap<>();
        query.put("code","abc");
        userMapper.selectByMap(query);
    }

    @Override
    public void testQuery() {
        testPageQuery();
    }
}
