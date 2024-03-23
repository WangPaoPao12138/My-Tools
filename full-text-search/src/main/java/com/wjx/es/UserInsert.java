package com.wjx.es;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjx.es.template.ConnectElasticsearchTemplate;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * @author Gin
 * @description
 * @date 2024/3/23 16:44
 */
public class UserInsert {
    public static void main(String[] args) {
        ConnectElasticsearchTemplate.connect(client -> {
            // 新增文档 - 请求对象
            IndexRequest request = new IndexRequest();
            // 设置索引及唯一性标识
            request.index("user").id("1001");
            // 创建数据对象
            User user = new User();
            user.setName("Gin");
            user.setAge(26);
            user.setSex("男");
            // 添加文档数据，数据格式为 JSON 格式
            ObjectMapper objectMapper = new ObjectMapper();
            String productJson = objectMapper.writeValueAsString(user);
            request.source(productJson, XContentType.JSON);
            // 客户端发送请求，获取响应对象
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            //3.打印结果信息
            System.out.println("_index:" + response.getIndex());
            System.out.println("_id:" + response.getId());
            System.out.println("_result:" + response.getResult());
        });
    }
}
