package com.example.demo.nio.netty_gateway;

import com.example.demo.nio.netty_gateway.inbound.HttpInboundServer;

import java.util.Arrays;

/**
 * @author Leo liang
 * @Date 2022/1/22
 */
public class NettyGateway {

    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "3.0.0";

    public static void main(String[] args) {
        String proxyPort = System.getProperty("proxyPort","8888");

        // 这是之前的单个后端url的例子
//        String proxyServer = System.getProperty("proxyServer","http://localhost:8088");
//          //  http://localhost:8888/api/hello  ==> gateway API
//          //  http://localhost:8088/api/hello  ==> backend service
        // java -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar  #作为后端服务


        // 这是多个后端url走随机路由的例子
//        String proxyServers = System.getProperty("proxyServers","http://localhost:8802,http://localhost:8803");
        //这里的代理服务器就是你要访问的后端服务器的域名
        String proxyServers = System.getProperty("proxyServers","https://www.baidu.com,https://www.bing.com");
        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" starting...");
        HttpInboundServer server = new HttpInboundServer(port, Arrays.asList(proxyServers.split(",")));
//        HttpInboundServer server = new HttpInboundServer(port, Arrays.asList(proxyServer));
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port + " for server:" + server.toString());
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
