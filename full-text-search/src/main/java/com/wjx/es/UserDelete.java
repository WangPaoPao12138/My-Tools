package com.wjx.es;

import com.wjx.es.template.ConnectElasticsearchTemplate;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;

/**
 * @author Gin
 * @description
 * @date 2024/3/23 16:55
 */
public class UserDelete {
    public static void main(String[] args) {
        ConnectElasticsearchTemplate.connect(client -> {
            //创建请求对象
            DeleteRequest request = new DeleteRequest().index("user").id("1001");
            //客户端发送请求，获取响应对象
            DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
            //打印信息
            System.out.println(response.toString());

        });
    }
}
