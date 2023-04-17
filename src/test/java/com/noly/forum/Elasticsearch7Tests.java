package com.noly.forum;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ForumApplication.class)
public class Elasticsearch7Tests {

    public static void main(String[] args) throws IOException {
        // 创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );



//        // 创建索引 - 请求对象
//        CreateIndexRequest request = new CreateIndexRequest("user");
//        // 发送请求，获取响应
//        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
//        boolean acknowledged = response.isAcknowledged();
//        // 响应状态
//        System.out.println("操作状态 = " + acknowledged);


        // 删除索引 - 请求对象
        DeleteIndexRequest request1 = new DeleteIndexRequest("user");
        // 发送请求，获取响应
        AcknowledgedResponse response1 = client.indices().delete(request1, RequestOptions.DEFAULT);
        // 操作结果
        System.out.println("操作结果 ： " + response1.isAcknowledged());














        // 关闭客户端连接
        try {
            client.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }






}
