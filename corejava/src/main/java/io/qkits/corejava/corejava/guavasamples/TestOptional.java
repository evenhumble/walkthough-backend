package io.qkits.corejava.corejava.guavasamples;

import com.google.common.base.Optional;
import org.testng.annotations.Test;

public class TestOptional {

    @Test
    public void testOptional1() {

        for(int i = 0; i < 100; i++) {
            Optional<User> p = UserFactory.create();
            System.out.println(p.isPresent()); //false 则引用缺失
            if(p.isPresent()) {
                p.get(); //引用存在，获取引用对象
            }
            p.or(new User("default", 0)); //引用缺失时的默认值
            p.orNull(); //引用缺失，则返回null
            p.asSet(); //引用的集合，弱引用缺失，则返回空集合
        }
    }
}
