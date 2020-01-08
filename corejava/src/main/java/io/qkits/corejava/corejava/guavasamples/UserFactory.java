package io.qkits.corejava.corejava.guavasamples;

import com.google.common.base.Optional;

import java.util.Random;

public class UserFactory {

    public static Optional<User> create() {
        Random random = new Random(System.currentTimeMillis());
        User user = null;
        if(random.nextInt(100) % 10 != 0) {
            user = new User("napos", 10);
        }
//        Optional.of(user); //user 不能为null， 为null则快速失败
        return Optional.fromNullable(user); // user 可以是null
    }
}
