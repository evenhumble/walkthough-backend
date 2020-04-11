package io.qkits.benchmarks.db.jpa;

import io.qkits.benchmarks.db.core.DaoBenchmarkService;
import io.qkits.benchmarks.db.jpa.entity.User;
import io.qkits.benchmarks.db.jpa.service.UserService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * QMETA
 * created at: 2020/4/11
 * created by: patrick
 **/
@Service
public class JpaDaoBenchmarkService implements DaoBenchmarkService {
    private AtomicInteger USER_ID = new AtomicInteger();

    final UserService userService ;

    public JpaDaoBenchmarkService(UserService userService) {
        this.userService = userService;
    }

    private User next() {
        User user = new User();
        user.setId(USER_ID.getAndIncrement());
        user.setCode("abc");
        return user;
    }
    @Override
    public void testAdd() {
        User user = next();
        userService.add(user);
    }

    @Override
    public void testUnique() {
        userService.findById(1);
    }

    @Override
    public void testUpdateById() {
        User user = new User();
        user.setId(1);
        user.setCode("abc");
        userService.update(user);
    }

    @Override
    public void testPageQuery() {
        userService.customQuery(new HashMap());
    }

    @Override
    public void testQuery() {
        testPageQuery();
    }
}
