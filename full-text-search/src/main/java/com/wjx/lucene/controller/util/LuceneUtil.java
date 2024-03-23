package com.wjx.lucene.controller.util;

import com.wjx.lucene.dao.StudentInfo;
import com.wjx.lucene.mapper.StudentInfoMapper;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.ngram.NGramTokenizer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gin
 * @description
 * @date 2023/12/15 22:50
 */
@Component
public class LuceneUtil {
    public static String path = "C:\\Users\\11625\\Desktop\\Game\\idx";

    public static IndexReader reader;

    static {
        try {
            reader = DirectoryReader.open(FSDirectory.open(new File(path).toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Resource
    StudentInfoMapper studentInfoMapper;

    public void createIndex() throws IOException {
        //指定索引库存放的路径
        Directory directory = FSDirectory.open(new File(path).toPath());
        //索引库还可以存放到内存中
        //Directory directory = new RAMDirectory();
        //创建indexwriterCofig对象
        // 设置打开方式：OpenMode.APPEND 追加新索引。OpenMode.CREATE 新建
        IndexWriterConfig config = new IndexWriterConfig(new Analyzer() {
            @Override
            protected TokenStreamComponents createComponents(String s) {
                Tokenizer tokenizer = new NGramTokenizer(1, 10);
                return new TokenStreamComponents(tokenizer);
            }
        });
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        //创建indexwriter对象
        IndexWriter indexWriter = new IndexWriter(directory, config);
        ArrayList<Document> docs = new ArrayList<>();
        List<StudentInfo> studentInfos = studentInfoMapper.selectAll();
        for (StudentInfo studentInfo : studentInfos) {
            //创建document对象
            Document document = new Document();
            document.add(new StoredField("id", String.valueOf(studentInfo.getId())));
            document.add(new TextField("studentId", String.valueOf(studentInfo.getStudentId()), Field.Store.YES));
            document.add(new TextField("classId", String.valueOf(studentInfo.getClassId()), Field.Store.YES));
            document.add(new TextField("courseId", String.valueOf(studentInfo.getCourseId()), Field.Store.YES));
            document.add(new TextField("name", String.valueOf(studentInfo.getName()), Field.Store.YES));
            document.add(new StoredField("createTime", String.valueOf(studentInfo.getCreateTime())));
            docs.add(document);
        }
        //创建索引，并写入索引库
        indexWriter.addDocuments(docs);
        //提交索引数据
        indexWriter.commit();
        //关闭indexwriter
        indexWriter.close();
        directory.close();
    }
}
