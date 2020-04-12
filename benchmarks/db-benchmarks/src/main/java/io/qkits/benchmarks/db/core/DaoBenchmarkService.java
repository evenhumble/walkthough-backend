package io.qkits.benchmarks.db.core;

import io.qkits.benchmarks.db.entity.User;

import java.util.concurrent.atomic.AtomicInteger;

public interface DaoBenchmarkService {
    public static AtomicInteger USER_ID = new AtomicInteger(10);

    default User next() {
        User user = new User();
        user.setId(USER_ID.getAndIncrement());
        user.setCode("abc");
        return user;
    }
    /**
     * Insert One
     */
    void testAdd();
    /**
     *  select By id which is primary key
     */
    void testUnique();
    /**
     * update by id which is primary key
     */
    void testUpdateById();
    /**
     * test page query it depends on how much data it is
     */
    void testPageQuery();
    /**
     * test complex query
     */
    void testQuery();

}
