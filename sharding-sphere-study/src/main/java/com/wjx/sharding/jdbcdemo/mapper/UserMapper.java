package com.wjx.sharding.jdbcdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjx.sharding.jdbcdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Gin
 * @description
 * @date 2024/3/3 11:35
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
