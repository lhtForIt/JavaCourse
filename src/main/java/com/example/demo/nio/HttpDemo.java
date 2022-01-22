package com.example.demo.nio;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Random;

/**
 * @author Leo liang
 * @Date 2022/1/16
 */
public class HttpDemo {

    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        String[] strs = {"", "test"};
        String temp = strs[new Random().nextInt(2)];
        HttpGet httpGet = new HttpGet("http://127.0.0.1:8808/");

        CloseableHttpResponse response = null;
        try {

            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(content);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }

    }

}
