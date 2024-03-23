package com.wjx.es;

import com.wjx.es.template.ConnectElasticsearchTemplate;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.client.RequestOptions;

/**
 * @author Gin
 * @description
 * @date 2024/3/23 17:29
 */
public class UserBatchDelete {
    public static void main(String[] args) {
        ConnectElasticsearchTemplate.connect(client -> {
            //创建批量删除请求对象
            BulkRequest request = new BulkRequest();
            request.add(new DeleteRequest().index("user").id("1003"));
            request.add(new DeleteRequest().index("user").id("1004"));
            request.add(new DeleteRequest().index("user").id("1005"));
            //客户端发送请求，获取响应对象
            BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
            //打印结果信息
            System.out.println("took:" + responses.getTook());
            System.out.println("items:" + responses.getItems());
        });
    }
}
