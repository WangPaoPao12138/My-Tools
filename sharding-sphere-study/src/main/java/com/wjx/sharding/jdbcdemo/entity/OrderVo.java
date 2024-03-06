package com.wjx.sharding.jdbcdemo.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Gin
 * @description
 * @date 2024/3/3 23:50
 */

@Data
public class OrderVo {
    private String orderNo;
    private BigDecimal amount;
}