package com.wjx.es;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * 连接ES操作
 *
 * @author Gin
 * @description
 * @date 2024/3/23 15:59
 */
public class HelloElasticsearch {

    public static void main(String[] args) throws IOException {
        // 创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
        // 关闭客户端连接
        client.close();
    }
}
