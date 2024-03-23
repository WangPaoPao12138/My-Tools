package com.wjx.es.template;

import org.elasticsearch.client.RestHighLevelClient;

/**
 * 连接模板接口
 *
 * @author Gin
 * @description
 * @date 2024/3/23 16:23
 */
public interface ConnectElasticsearch {
    void doSomething(RestHighLevelClient client) throws Exception;
}
