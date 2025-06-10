package com.sky.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @Author TangYu
 * @Date 2025/5/16 14:08
 */
@SpringBootTest
public class HttpClientTest {

    /**
     * 通过httpClient发送GET方式的请求
     */
    @Test
    public void testGET() throws IOException {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建请求对象
        HttpGet httpGet = new HttpGet("http://localhost:8080/user/shop/status");

        //发送请求，接受响应结果
        CloseableHttpResponse response = httpClient.execute(httpGet);

        //获取服务端返回的状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("服务端返回的状态:" + statusCode);

        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        System.out.println("服务端返回的数据为:" + body);

        //关闭资源
        response.close();
        httpClient.close();

    }

    /**
     * 通过httpClient发送POST方式的请求
     */
    @Test
    public void testPOST() throws IOException {
        //1.创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //2.创建请求对象
        HttpPost httpPost = new HttpPost("http://localhost:8080/admin/employee/login");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","admin");
        jsonObject.put("password","123456");

        StringEntity stringEntity = new StringEntity(jsonObject.toString());
        //指定编码方式
        stringEntity.setContentEncoding("utf-8");
        //数据格式
        stringEntity.setContentType("application/json");

        httpPost.setEntity(stringEntity);

        //3.发送请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        //4.解析返回结果
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("服务端返回的状态:" + statusCode);

        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        System.out.println("响应数据为:" + body);

        //关闭资源
        response.close();
        httpClient.close();
    }

}
