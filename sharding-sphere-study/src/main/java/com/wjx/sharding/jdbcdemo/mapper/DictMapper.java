package com.wjx.sharding.jdbcdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjx.sharding.jdbcdemo.entity.Dict;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Gin
 * @description
 * @date 2024/3/4 0:00
 */
@Mapper
public interface DictMapper extends BaseMapper<Dict> {
}