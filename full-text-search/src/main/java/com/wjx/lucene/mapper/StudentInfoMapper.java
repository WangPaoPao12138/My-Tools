package com.wjx.lucene.mapper;

import com.wjx.lucene.dao.StudentInfo;

import java.util.List;

/**
 * 学生信息表
 *
 * @author Gin
 * @description
 * @date 2023/12/15 21:36
 */
public interface StudentInfoMapper {

   List<StudentInfo> selectLike(String key);
   List<StudentInfo> selectAll();

}
