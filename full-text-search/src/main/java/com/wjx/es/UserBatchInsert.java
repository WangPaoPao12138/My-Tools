package com.wjx.es;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjx.es.template.ConnectElasticsearchTemplate;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * @author Gin
 * @description
 * @date 2024/3/23 17:05
 */
public class UserBatchInsert {
    public static void main(String[] args) {
        ConnectElasticsearchTemplate.connect(client -> {
            // 批插 - 请求对象
            BulkRequest request = new BulkRequest();
            // 批量插入数据
            request.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON,"name","zhangsan"));
            request.add(new IndexRequest().index("user").id("1004").source(XContentType.JSON,"name","lisi"));
            request.add(new IndexRequest().index("user").id("1005").source(XContentType.JSON,"name","wangwu"));

            // 客户端发送请求，获取响应对象
            BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
            //3.打印结果信息
            System.out.println("took:" + response.getTook());
            System.out.println("items:" + response.getItems());
        });
    }
}
