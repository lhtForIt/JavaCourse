package com.example.demo.mq.CustomMq.producer;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author Leo liang
 * @Date 2022/4/11
 */
public class MqProduce {

    private CloseableHttpClient httpClient;

    public void send() throws IOException {
        httpClient = HttpClients.createDefault();
        HttpUriRequest httpUriRequest = new HttpPost("http://localhost:8080/leomq/send");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpUriRequest);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(content);
            }
        }finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }



    }





}
