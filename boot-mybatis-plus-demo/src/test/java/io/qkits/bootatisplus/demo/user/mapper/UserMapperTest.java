package io.qkits.bootatisplus.demo.user.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.qkits.bootatisplus.demo.base.page.UserPage;
import io.qkits.bootatisplus.demo.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@Slf4j
@SpringBootTest
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testGetOfBaseMapper(){
      log.info("start getting user information");
        List<User> users = userMapper.selectList(null);
        assertTrue(users.size()>2);
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
        assertThat(count).isEqualTo(1);
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
        int result = userMapper.deleteById(5L);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void updateOfBaseMapper(){
        log.info("start update ......");
        User user = new User().setId(1L).setEmail("test@test.com").setAge(12);
        int result = userMapper.updateById(user);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void updateWithQueryWrapper(){
        int updatedCount = userMapper.update(
                new User().setName("mp"),
                Wrappers.<User>lambdaUpdate()
                .set(User::getAge,3)
                .eq(User::getId,2)
        );

        assertThat(updatedCount).isEqualTo(1);
        User user = userMapper.selectById(2);
        assertThat(user.getAge()).isEqualTo(3);
        assertThat(user.getName()).isEqualTo("mp");

        userMapper.update(
                new User().setEmail("test@test.com"),
                new QueryWrapper<User>()
                .lambda().eq(User::getId,2)
        );

    }
    @Test
    public void dynamicTest() {
        for (int i = 0; i < 6; i++) {
            User user = userMapper.selectById(1L);
            System.out.println(user.getName());
        }
    }

    @Test
    public void testXMLSQL(){
       List<User> result =  userMapper.findList(new User().setName("test"));
        System.out.println(result);
    }

    @Test
    public void testAbstractMethods(){
        int count = userMapper.selectCount(new QueryWrapper<>());
        userMapper.myInsertAll(new User()
                .setAge(12).setCount(1).setEmail("testBatchInsertAll@test.com")
        .setName("myInsertbatch"));
        int counterLater = userMapper.selectCount(new QueryWrapper<>());
        System.out.println(counterLater-count);
    }
    @Test
    public void testPage(){
        UserPage selectedPage = new UserPage(1,2).setSelectInt(10);
        UserPage userPage = userMapper.selectUserPage(selectedPage);
        System.out.println(userPage);
    }


    @Test
    public void testUpdateByIdSucc() {
        User user = new User();
        user.setAge(18);
        user.setEmail("test@baomidou.com");
        user.setName("optlocker");
        user.setVersion(1);
        userMapper.insert(user);
        Long id = user.getId();

        User userUpdate = new User();
        userUpdate.setId(id);
        userUpdate.setAge(19);
        userUpdate.setVersion(1);
        assertEquals("Should update success", 1, userMapper.updateById(userUpdate));
        assertEquals("Should version = version+1", 2, userUpdate.getVersion().intValue());
    }

    @Test
    public void testUpdateByIdFail() {
        User user = new User();
        user.setAge(18);
        user.setEmail("test@baomidou.com");
        user.setName("optlocker");
        user.setVersion(1);
        userMapper.insert(user);
        Long id = user.getId();

        User userUpdate = new User();
        userUpdate.setId(id);
        userUpdate.setAge(19);
        userUpdate.setVersion(0);
        assertEquals("Should update failed due to incorrect version(actually 1, but 0 passed in)", 0, userMapper.updateById(userUpdate));
    }

    @Test
    public void testUpdateByIdSuccWithNoVersion() {
        User user = new User();
        user.setAge(18);
        user.setEmail("test@baomidou.com");
        user.setName("optlocker");
        user.setVersion(1);
        userMapper.insert(user);
        Long id = user.getId();

        User userUpdate = new User();
        userUpdate.setId(id);
        userUpdate.setAge(19);
        userUpdate.setVersion(0);
        assertEquals("Should update success as no version passed in", 1, userMapper.updateById(userUpdate));
        User updated = userMapper.selectById(id);
        assertEquals("Version not changed", 1, updated.getVersion().intValue());
        assertEquals("Age updated", 19, updated.getAge().intValue());
    }

    /**
     * 批量更新带乐观锁
     * <p>
     * update(et,ew) et:必须带上version的值才会触发乐观锁
     */
    @Test
    public void testUpdateByEntitySucc() {
        QueryWrapper<User> ew = new QueryWrapper<>();
        ew.eq("version", 1);
        int count = userMapper.selectCount(ew);

        User entity = new User();
        entity.setAge(28);
        entity.setVersion(1);

        assertEquals("updated records should be same", count, userMapper.update(entity, null));
        ew = new QueryWrapper<>();
        ew.eq("version", 1);
        assertEquals("No records found with version=1", 0, userMapper.selectCount(ew).intValue());
        ew = new QueryWrapper<>();
        ew.eq("version", 2);
        assertEquals("All records with version=1 should be updated to version=2", count, userMapper.selectCount(ew).intValue());
    }
}