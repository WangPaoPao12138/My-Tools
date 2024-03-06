package com.wjx.sharding.jdbcdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Gin
 * @description
 * @date 2024/3/3 21:26
 */
@TableName("t_order")
@Data
public class Order {
    //当配置了shardingsphere-jdbc的分布式序列时，自动使用shardingsphere-jdbc的分布式序列
    //当没有配置shardingsphere-jdbc的分布式序列时，自动依赖数据库的主键自增策略
    @TableId(type = IdType.AUTO)//依赖数据库的主键自增策略
//    @TableId(type = IdType.ASSIGN_ID)//分布式id
    private Long id;
    private String orderNo;
    private Long userId;
    private BigDecimal amount;
}
