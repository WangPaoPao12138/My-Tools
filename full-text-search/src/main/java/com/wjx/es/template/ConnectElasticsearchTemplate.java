package com.wjx.es.template;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * 模板
 *
 * @author Gin
 * @description
 * @date 2024/3/23 16:22
 */
@Slf4j
public class ConnectElasticsearchTemplate {
    public static void connect(ConnectElasticsearch task) {
        // 创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
        try {
            task.doSomething(client);
        } catch (Exception e) {
            log.error("ES操作失败{}", e);
        } finally {
            // 关闭客户端连接
            try {
                client.close();
            } catch (IOException e) {
                log.error("ES连接关闭失败{}", e);
            }
        }
    }

}
