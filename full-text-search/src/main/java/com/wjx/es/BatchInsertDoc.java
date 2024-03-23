package com.wjx.es;

import com.wjx.es.template.ConnectElasticsearchTemplate;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * @author Gin
 * @description
 * @date 2024/3/23 17:36
 */
public class BatchInsertDoc {
    public static void main(String[] args) {
        ConnectElasticsearchTemplate.connect(client -> {
            // 批插 - 请求对象
            BulkRequest request = new BulkRequest();
            // 批量插入数据
            request.add(new IndexRequest().index("user").id("1001").source(XContentType.JSON, "name", "zhangsan", "age", "10", "sex","女"));
            request.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON, "name", "lisi", "age", "30", "sex","女"));
            request.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON, "name", "wangwu1", "age", "40", "sex","男"));
            request.add(new IndexRequest().index("user").id("1004").source(XContentType.JSON, "name", "wangwu2", "age", "20", "sex","女"));
            request.add(new IndexRequest().index("user").id("1005").source(XContentType.JSON, "name", "wangwu3", "age", "50", "sex","男"));
            request.add(new IndexRequest().index("user").id("1006").source(XContentType.JSON, "name", "wangwu4", "age", "20", "sex","男"));
            // 客户端发送请求，获取响应对象
            BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
            //3.打印结果信息
            System.out.println("took:" + response.getTook());
            System.out.println("items:" + response.getItems());
        });
    }
}
