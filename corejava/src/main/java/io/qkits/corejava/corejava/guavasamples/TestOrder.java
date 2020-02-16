package io.qkits.corejava.corejava.guavasamples;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.testng.annotations.Test;

import java.util.List;

public class TestOrder {

    @Test
    public void testOrder() {
        //定义一种排序器
        Ordering<User> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<User, String>() {
            @Override
            public String apply(User input) {
                return input.getName();
            }
        });
        List<User> users = Lists.newArrayList(
                new User("Li", 18),
                new User("Ai", 17),
                new User("Bi", 17),
                new User("Zi", 17)
        );
        System.out.println(ordering.compare(new User("Li", 18), new User("Li", 17)));
        User user = ordering.max(users);
        System.out.println(user.getName());
        List<User> users1 = ordering.greatestOf(users, 2); //取出最大的两个
        System.out.println(users1.size());
    }
}
