package com.wjx.es;

import com.wjx.es.template.ConnectElasticsearchTemplate;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;

/**
 * @author Gin
 * @description
 * @date 2024/3/23 16:55
 */
public class UserGet {
    public static void main(String[] args) {
        ConnectElasticsearchTemplate.connect(client -> {
            //1.创建请求对象
            GetRequest request = new GetRequest().index("user").id("1001");
            //2.客户端发送请求，获取响应对象
            GetResponse response = client.get(request, RequestOptions.DEFAULT);
            //3.打印结果信息
            System.out.println("_index:" + response.getIndex());
            System.out.println("_type:" + response.getType());
            System.out.println("_id:" + response.getId());
            System.out.println("source:" + response.getSourceAsString());
        });
    }
}
