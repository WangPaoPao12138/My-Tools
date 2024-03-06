package com.wjx.sharding.jdbcdemo;

import com.wjx.sharding.jdbcdemo.entity.User;
import com.wjx.sharding.jdbcdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Gin
 * @description
 * @date 2024/3/4 0:27
 */
@SpringBootTest
public class ShardingProxyDemoApplicationTests {
    @Autowired
    private UserMapper userMapper;

    /**
     * 读数据测试
     */
    @Test
    public void testSelectAll() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
}
