package com.example.demo.jvm02;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Leo liang
 * @Date 2022/1/16
 */
public class HttpDemo {

    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8801");

        CloseableHttpResponse response = null;
        try {

            response = httpClient.execute(httpGet);
            System.out.println(response);

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
