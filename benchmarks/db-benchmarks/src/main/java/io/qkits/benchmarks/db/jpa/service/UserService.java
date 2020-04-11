package io.qkits.benchmarks.db.jpa.service;

import io.qkits.benchmarks.db.entity.User;
import io.qkits.benchmarks.db.jpa.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * QMETA
 * created at: 2020/4/11
 * created by: patrick
 **/
@Service
public class UserService implements BaseService<User> {
    @Autowired
    private UserRepo userRepo;

    @Override
    public User add(User user) {
        return userRepo.save(user);
    }

    @Override
    public User findById(Integer id) {
        return userRepo.findById(id).get();
    }

    @Override
    public User update(User entity) {
        return userRepo.save(entity);
    }

    @Override
    public void customQuery(Map code) {
        userRepo.findAll(PageRequest.of(2,10));
    }
}
