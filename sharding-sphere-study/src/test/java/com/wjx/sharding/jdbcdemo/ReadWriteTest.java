package com.wjx.sharding.jdbcdemo;

import com.wjx.sharding.jdbcdemo.entity.Order;
import com.wjx.sharding.jdbcdemo.entity.User;
import com.wjx.sharding.jdbcdemo.mapper.OrderMapper;
import com.wjx.sharding.jdbcdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Gin
 * @description
 * @date 2024/3/3 11:49
 */
@SpringBootTest
public class ReadWriteTest {
    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderMapper orderMapper;

    @Test
    void testInsertUser() {
        User user = new User();
        user.setUname("王泡泡");
        userMapper.insert(user);
    }

    @Transactional
    @Test
    void testTransaction() {
        User user = new User();
        user.setUname("小李");
        userMapper.insert(user);
        userMapper.selectList(null);
    }

    @Test
    void testSelectAll() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
        List<User> users1 = userMapper.selectList(null);
        users1.forEach(System.out::println);
        List<User> users2 = userMapper.selectList(null);
        users2.forEach(System.out::println);
        List<User> users3 = userMapper.selectList(null);
        users3.forEach(System.out::println);
    }


}
