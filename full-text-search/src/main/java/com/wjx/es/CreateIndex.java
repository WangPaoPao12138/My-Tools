package com.wjx.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author Gin
 * @description
 * @date 2024/3/23 16:03
 */
public class CreateIndex {

    public static void main(String[] args) throws IOException {
        // 创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // 创建索引 - 请求对象-注意这里是新的对象
        //原来
        //org.elasticsearch.action.admin.indices.create.CreateIndexRequest
        //新的
        //org.elasticsearch.client.indices.CreateIndexRequest
        CreateIndexRequest request = new CreateIndexRequest("user2");
        // 发送请求，获取响应
        CreateIndexResponse response = client.indices().create(request,
                RequestOptions.DEFAULT);
        // 响应状态
        boolean acknowledged = response.isAcknowledged();
        System.out.println("操作状态 = " + acknowledged);

        // 关闭客户端连接
        client.close();
    }
}