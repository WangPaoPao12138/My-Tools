package com.wjx.sharding.jdbcdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjx.sharding.jdbcdemo.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Gin
 * @description
 * @date 2024/3/3 23:44
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
}