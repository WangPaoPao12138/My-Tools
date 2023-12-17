package com.wjx.controller;

import com.wjx.controller.util.LuceneUtil;
import com.wjx.dao.StudentInfo;
import com.wjx.mapper.StudentInfoMapper;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于百万数据表测试
 * controller
 *
 * @author Gin
 * @description
 * @date 2023/12/15 22:13
 */
@RestController
@RequestMapping("lucene")
public class LuceneController {
    @Resource
    private StudentInfoMapper studentInfoMapper;
    @Resource
    private LuceneUtil luceneUtil;

    @GetMapping("sql")
    public List<StudentInfo> getFromSql(@RequestParam("key") String key) {
        long l = System.currentTimeMillis();
        //1025
        //1184
        //855
        List<StudentInfo> studentInfo = studentInfoMapper.selectLike(key);
        System.out.println("sql 检索条件：" + key + " cost：" + (System.currentTimeMillis() - l));
        return studentInfo;
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @GetMapping("test")
    public List<StudentInfo> getFromLucene(String key) {
        long l = System.currentTimeMillis();
        List<StudentInfo> studentInfos = new ArrayList<>();
        try {
            //创建搜索器
            IndexReader reader = luceneUtil.reader;
            IndexSearcher searcher = new IndexSearcher(reader);
            //查询条件
            BooleanQuery.Builder builder = new BooleanQuery.Builder();
            //模糊查询
            //WildcardQuery
            //1333
            //1206
            //TermQuery
            //867
            //636
            //lucene cost：1125
            //sql cost：1134
            TermQuery name = new TermQuery(new Term("name", key));
            TermQuery classId = new TermQuery(new Term("classId", key));
            TermQuery courseId = new TermQuery(new Term("courseId", key));
            builder.add(name, BooleanClause.Occur.SHOULD);
            builder.add(classId, BooleanClause.Occur.SHOULD);
            builder.add(courseId, BooleanClause.Occur.SHOULD);
            BooleanQuery query = builder.build();
            /**
             * 这里可以做分页
             * https://blog.csdn.net/yelllowcong/article/details/78698516
             */
            TopDocs topDocs = searcher.search(query, Integer.MAX_VALUE);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            System.out.println("lucene 开始处理 doc 数据" + (System.currentTimeMillis() - l));
            for (ScoreDoc scoreDoc : scoreDocs) {
                int docID = scoreDoc.doc;
                Document doc = reader.document(docID);
                StudentInfo studentInfo = new StudentInfo();
                studentInfo.setStudentId(Integer.parseInt(doc.get("studentId")));
                studentInfo.setClassId(Integer.parseInt(doc.get("classId")));
                studentInfo.setCourseId(Integer.parseInt(doc.get("courseId")));
                studentInfo.setId(Integer.parseInt(doc.get("id")));
                studentInfo.setName(doc.get("name"));
//                studentInfo.setCreateTime(sdf.parse(doc.get("createTime")));
                studentInfos.add(studentInfo);
            }
            System.out.println("lucene 结束处理 doc 数据" + (System.currentTimeMillis() - l));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("lucene 检索条件：" + key + " cost：" + (System.currentTimeMillis() - l));
        return studentInfos;
    }
}
